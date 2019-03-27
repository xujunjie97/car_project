package com.bishe.hunter.web;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xujunjie
 */
@RestController
@Slf4j
@RequestMapping("/qr")
public class QrController {


    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    //输入二维码要生成的内容
    @RequestMapping(value = "/open")
    public ModelAndView open(String qrid1){
        ModelAndView mv=new ModelAndView();
        mv.addObject("qrid",qrid1);
        mv.setViewName("/qrcode.btl");
        return mv;
    }

    /**
     * 生成二维码方法
     *
     * @param  content 要生成的内容
     * @param resp response对象
     * @throws Exception 抛出异常
     */
    @GetMapping(value = "/qrcode")
    public void getQrcode(String content, HttpServletResponse resp) throws Exception {
        ServletOutputStream stream = null;
        try {
            stream = resp.getOutputStream();
            Map<EncodeHintType, Object> hints = new HashMap<>();
            //编码
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            //边框距
            hints.put(EncodeHintType.MARGIN, 0);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bm = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
            MatrixToImageWriter.writeToStream(bm, "png", stream);
        } catch (WriterException e) {
            e.getStackTrace();

        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
}
