package com.lc.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.lc.entity.Donation;
import com.lc.entity.User;
import com.lc.service.DonationService;
import com.lc.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 捐赠信息控制层
 */
@RestController
@RequestMapping("/donation")
public class DonationController {

    @Autowired
    DonationService donationService;


    /**
     * 捐赠信息数据表格接口
     */
    @RequestMapping(value="/getTableData", produces="application/json; charset=utf-8")
    public String getTableData(@RequestBody Donation donation) {
        Map<String, Object> map = donationService.selectPage(donation);
        return JSON.toJSONString(map);
    }

    /**
     * 后台捐赠信息保存
     */
    @RequestMapping("/saveDonation")
    public String saveDonation(@RequestBody Donation donation) {
        return donationService.save(donation);
    }

    /**
     * 前台捐赠信息保存
     */
    @RequestMapping("/insertDonationList")
    public String insertDonationList(@RequestBody List<Donation> list) {
        return donationService.insertDonationList(list);
    }

    /**
     * 捐赠信息删除(物理删除)
     */
    @RequestMapping("/deleteDonation")
    public String deleteDonation(String id) {
        return donationService.deletePhysical(id);
    }

    /**
     * 我的捐赠记录数据获取
     */
    @RequestMapping("/selfDonation")
    public List<Map<String, Object>> selfDonation(){
        User currentUser = UserContext.getCurrentUser();
        List<Map<String, Object>> listMap = donationService.countSelfDonation(currentUser.getId());
        return listMap;
    }

    /**
     * 后台修改捐赠记录状态
     */
    @RequestMapping("/updateVerify")
    public String updateVerify(String id, Integer verify){
        return donationService.updateVerifyById(id, verify);
    }


    /**
     * 前台页面第一个饼状图数据接口
     */
    @RequestMapping("/echartsDataOne")
    public List<Map<String, String>> echartsDataOne(){
        List<Donation> allList = donationService.selectAllList();
        Map<String, List<Donation>> allMap = allList.stream().peek(o -> {
            if(o.getKind() == 0){
                o.setKindName("上衣");
            }else if(o.getKind() == 1){
                o.setKindName("裤子");
            }else if(o.getKind() == 2){
                o.setKindName("袜子");
            }else if(o.getKind() == 3){
                o.setKindName("手套");
            }else if(o.getKind() == 4){
                o.setKindName("帽子");
            }else if(o.getKind() == 5){
                o.setKindName("其他");
            }
        }).collect(Collectors.groupingBy(Donation::getKindName));
        List<Map<String, String>> listMap = new ArrayList<>();
        for(Map.Entry<String, List<Donation>> map : allMap.entrySet()){
            Double sum = map.getValue().stream().mapToDouble(Donation::getNumber).sum();
            Map<String, String> itemMap = new HashMap<String, String>();
            itemMap.put("value", String.valueOf(sum));
            itemMap.put("name", map.getKey());
            listMap.add(itemMap);
        }
        return listMap;
    }

    /**
     * 前台页面第二个柱状图数据接口
     */
    @RequestMapping("/echartsDataTwo")
    public Map<String, List<String>> echartsDataTwo(){
        Map<String, List<String>> resultMap = new HashMap<>();
        //获取最近七天的时间段（往前找3天+往后找三天+今天一天）
        List<String> dateList = new ArrayList<>();
        String today= DateUtil.today();
        Date date = DateUtil.parse(today);
        for(int i=0; i<7; i++){
            String d = DateUtil.format(DateUtil.offset(date, DateField.DAY_OF_MONTH, -6 + i), "yyyy-MM-dd");
            dateList.add(d);
        }
        //根据日期获取数据
        List<String> dataList = new ArrayList<>();
        List<Donation> allList = donationService.selectAllList();
        for(String currentDate : dateList){
            List<Donation> list = allList.stream().filter(o -> currentDate.equals(o.getCreateDate().split(" ")[0])).collect(Collectors.toList());
            if(list.isEmpty()){
                dataList.add(String.valueOf(0));
            }else{
                dataList.add(String.valueOf(list.stream().mapToDouble(Donation::getNumber).sum()));
            }
        }
        resultMap.put("dateList", dateList);
        resultMap.put("dataList", dataList);
        return resultMap;
    }

    /**
     * 前台页面第三个折现图数据接口
     */
    @RequestMapping("/echartsDataThree")
    public Map<String, List<String>> echartsDataThree(){
        Map<String, List<String>> resultMap = new HashMap<>();
        //获取最近七天的时间段（往前找6天+今天一天）
        List<String> dateList = new ArrayList<>();
        String today= DateUtil.today();
        Date date = DateUtil.parse(today);
        for(int i=0; i<7; i++){
            String d = DateUtil.format(DateUtil.offset(date, DateField.DAY_OF_MONTH, -6 + i), "yyyy-MM-dd");
            dateList.add(d);
        }
        //根据日期获取数据
        List<Donation> allList = donationService.selectAllList();
        List<String> agreeList = new ArrayList<>();
        List<String> refuseList = new ArrayList<>();
        List<String> waitList = new ArrayList<>();
        for(String currentDate : dateList){
            List<Donation> list = allList.stream().filter(o -> currentDate.equals(o.getCreateDate().split(" ")[0])).collect(Collectors.toList());
            agreeList.add(String.valueOf(list.stream().filter(o -> o.getVerify() == 1).count()));
            refuseList.add(String.valueOf(list.stream().filter(o -> o.getVerify() == 2).count()));
            waitList.add(String.valueOf(list.stream().filter(o -> o.getVerify() == 0).count()));
        }
        resultMap.put("dateList", dateList);
        resultMap.put("agreeList", agreeList);
        resultMap.put("refuseList", refuseList);
        resultMap.put("waitList", waitList);
        return resultMap;

    }




}
