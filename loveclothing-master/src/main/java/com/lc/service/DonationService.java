package com.lc.service;

import com.alibaba.fastjson.JSON;
import com.lc.base.BaseCrudService;
import com.lc.dao.DonationDao;
import com.lc.entity.Donation;
import com.lc.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DonationService extends BaseCrudService<DonationDao, Donation> {

    @Autowired
    DonationDao donationDao;

    /**
     * 根据List新增
     */
    @Transactional(readOnly = false)
    public String insertDonationList(List<Donation> list){
        list = list.stream().peek(o -> {
            o.setUserId(UserContext.getCurrentUser().getId());
            o.preInsert();
        }).collect(Collectors.toList());
        donationDao.insertList(list);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "success");
        return JSON.toJSONString(map);
    }

    /**
     * 获取某个用户捐赠的各类别数量
     */
    @Transactional(readOnly = true)
    public List<Map<String, Object>> countSelfDonation(String userId){
        List<Map<String, Object>> list = new ArrayList<>();
        List<Donation> selfAllList = donationDao.selectByUserId(userId);
        selfAllList = selfAllList.stream().peek(o -> {
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
        }).collect(Collectors.toList());
        //累计提交数据统计
        Map<Integer, List<Donation>> mapDate = selfAllList.stream().collect(Collectors.groupingBy(Donation::getKind));
        Double totalSum = 0.0;
        for(Map.Entry<Integer, List<Donation>> map : mapDate.entrySet()){
            Double sum = map.getValue().stream().mapToDouble(Donation::getNumber).sum();
            Map<String, Object> itemMap = new HashMap<>();
            itemMap.put("kind", map.getKey().toString());
            itemMap.put("value", String.valueOf(sum));
            list.add(itemMap);
            totalSum += sum;
        }
        Map<String, Object> totalMap = new HashMap<>();
        totalMap.put("kind", "total");
        totalMap.put("value", String.valueOf(totalSum));
        list.add(totalMap);
        //放入最新提交的10条记录
        List<Donation> newList = selfAllList.stream().limit(10).collect(Collectors.toList());
        Map<String, Object> newListMap = new HashMap<>();
        newListMap.put("kind", "newList");
        newListMap.put("value", newList);
        list.add(newListMap);
        return list;
    }

    /**
     * 根据id修改状态
     */
    public String updateVerifyById(String id, Integer verify){
        donationDao.updateVerifyById(id, verify);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "success");
        return JSON.toJSONString(map);
    }

    /**
     * 根据用户id查询捐赠记录，按时间降序
     */
    public List<Donation> selectByUserId(String userId){
        return donationDao.selectByUserId(userId);
    }

}
