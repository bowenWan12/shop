package com.bww.shop.controller.admin;

import com.bww.shop.common.ResultCode;
import com.bww.shop.common.constant.GoodsEnums;
import com.bww.shop.controller.GoodsController;
import com.bww.shop.domain.Goods;
import com.bww.shop.domain.Result;
import com.bww.shop.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.apache.commons.lang3.StringUtils.leftPad;

@RestController
@RequestMapping("/admin/api/v1/goods")
public class GoodsAdminController extends GoodsController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Logger dataloger = LoggerFactory.getLogger("dataLogger");

    @Autowired
    private GoodsService goodsService;

    @Value("${me.upload.path}")
    private String baseFolderPath;

    /**
     * 根据id更新商品
     *
     * @return
     */
    @PutMapping("update_by_id")
    public Result updateById(@RequestBody Goods goods){
        dataloger.info("modul=shop_goods`api=update id={}", goods.getId());
        Result rs = new Result();
        int update = goodsService.updateGoodNameById(goods);
        if (update == 1){
            rs.setResultCode(ResultCode.SUCCESS);
        } else {
            rs.setResultCode(ResultCode.INTERFACE_INNER_INVOKE_ERROR);
        }
        return rs;
    }

    /**
     * 根据id删除商品
     *
     * @return
     */
    @PutMapping("del_by_id")
    public Result deleteById(@RequestBody Goods goods){
        dataloger.info("modul=shop_goods`api=delete id={}", goods.getId());
        Result rs = new Result();
        if ("2".equals(goods.getState())) {
            rs.setResultCode(ResultCode.DATA_IS_WRONG);
            return rs;
        }
        int update = goodsService.updateGoodNameById(goods);
        if (update == 1){
            rs.setResultCode(ResultCode.SUCCESS);
        } else {
            rs.setResultCode(ResultCode.INTERFACE_INNER_INVOKE_ERROR);
        }
        return rs;
    }

    /**
     * 根据mark删除商品
     * @param mark
     * @return
     */
    @GetMapping("/delete_by_mark")
    public Result deleteByMark(@RequestParam(value = "mark", required = true) String mark){
        dataloger.info("modul=shop_goods`api=delete mark={}", mark);
        Result rs = new Result();
        Goods goods = goodsService.findByMark(mark);

        System.out.println(goods == null);
        if (goods == null || StringUtils.isBlank(goods.getMark())) {
            rs.setResultCode(ResultCode.DATA_IS_WRONG);
            return rs;
        }
        goods.setState(GoodsEnums.GoodssState._1.getCode());
        int update = goodsService.updateGoodNameById(goods);

        if (update == 1) {
            rs.setResultCode(ResultCode.SUCCESS);
        }


        return rs;
    }

    /**
     * 添加商品
     * @param goods
     * @return
     */
    @PostMapping("/save")
    public Result upload(HttpServletRequest request, MultipartFile image, Goods goods) {

        Result rs = new Result();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        StringBuffer url = new StringBuffer();
        String filePath = sdf.format(new Date());

        File baseFolder = new File(baseFolderPath + filePath);
        if (!baseFolder.exists()) {
            baseFolder.mkdirs();
        }

        url.append(baseFolderPath)
                .append(File.separator)
                .append(filePath);

        String imgName = UUID.randomUUID() + "_" + image.getOriginalFilename().replaceAll(" ", "");

        try {

            File dest = new File(baseFolder, imgName);
            image.transferTo(dest);

            url.append("/").append(imgName);

            goods.setMark(GenerateGoodsMarkStr.goodsMark());
            goods.setState(GoodsEnums.GoodssState._0.getCode());
            goods.setImagePath(url.toString());

            goodsService.save(goods);

            rs.setResultCode(ResultCode.SUCCESS);

        } catch (IOException e) {
            logger.error("文件上传错误 , uri: {} , caused by: ", request.getRequestURI(), e);
            rs.setResultCode(ResultCode.UPLOAD_ERROR);
        }

        return rs;
    }

    public static class GenerateGoodsMarkStr{
        private static final transient AtomicInteger order_index = new AtomicInteger(0);

        public static String goodsMark(){
            SimpleDateFormat foramter = new SimpleDateFormat("yyMMddHHmmssSSS");
            String time = foramter.format(new Date());
            order_index.compareAndSet(999, 1);
            return time+leftPad(String.valueOf(order_index.incrementAndGet()%999),3,'0')+"000000000001";
        }
    }
}
