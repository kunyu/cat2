package com.crazyloong.cat.util;

import com.crazyloong.cat.rshainan.constant.RewardOrderType;
import com.crazyloong.cat.rshainan.dto.RewardOrder;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/5/29 22:58
 * @Description : Excel 导出工具类
 */
public class ExcelUtil {
    /**
     * 用户信息导出类
     * @param response 响应
     * @param fileName 文件名
     * @param dataList 导出的数据
     */
    public static void exportRewardOrder(HttpServletResponse response,String fileName,List<RewardOrder> dataList){
        //声明输出流
        OutputStream os = null;
        //设置响应头
        setResponseHeader(response,fileName);
        try {
            //获取输出流
            os = response.getOutputStream();
            //内存中保留1000条数据，以免内存溢出，其余写入硬盘
            SXSSFWorkbook wb = new SXSSFWorkbook(1000);
            for (RewardOrder rewardOrder: dataList) {
                // 如果不存在数据则获取下一个数据
                if (rewardOrder == null || rewardOrder.getList().size() == 0) {
                    continue;
                }
                // 获取该工作区的第一个sheet
                Sheet sheet = wb.createSheet(rewardOrder.getList().get(0).getId());
                int excelRow = 1;
                // 设置标题
                Row row = sheet.createRow(0);
                row.setHeight((short) (22.50 * 20));//设置行高
                row.createCell(0).setCellValue("Id");//为第一个单元格设值
                row.createCell(1).setCellValue("订单ID");//为第四个单元格设值
                row.createCell(2).setCellValue("时间");//为第二个单元格设值
                row.createCell(3).setCellValue("支付金额");//为第三个单元格设值
                row.createCell(4).setCellValue("预计奖励金额");//为第四个单元格设值
                row.createCell(5).setCellValue("订单状态");//为第四个单元格设值

                //设置内容行
                //外层for循环创建行
                for(RewardOrder.ListDTO order: rewardOrder.getList()){
                    Row dataRow = sheet.createRow(excelRow++);
                    dataRow.createCell(0).setCellValue(order.getId());
                    dataRow.createCell(1).setCellValue(order.getOrderId());
                    dataRow.createCell(2).setCellValue(order.getTime());
                    dataRow.createCell(3).setCellValue(order.getPayAmount());
                    dataRow.createCell(4).setCellValue(order.getGiveAmount());
                    dataRow.createCell(5).setCellValue(RewardOrderType.getDescByCode(String.valueOf(order.getType())));
                }
                sheet.setDefaultRowHeight((short) (16.5 * 20));
                //列宽自适应
                for (int i = 0; i <= 13; i++) {
                    sheet.autoSizeColumn(i);
                }
            }

            //将整理好的excel数据写入流中
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭输出流
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
        设置浏览器下载响应头
     */
    private static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

