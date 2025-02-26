package com.benzhz.qcfive.calculator.bo;

import cn.hutool.core.collection.CollectionUtil;
import com.benzhz.qcfive.utils.Mutil;
import lombok.Data;

import java.util.List;

import static com.benzhz.qcfive.common.constants.CommonConstants.RULE_CONTEXT;
import static com.benzhz.qcfive.common.constants.CommonConstants.SYSTEM_COMMON_PRECISION;

/**
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.pojo
 * @Project：qc-five
 * @name：SPCCalculationResult
 * @Date：2025/2/16 22:15
 * @Filename：SPCCalculationResult
 */

@Data
public class SPCCalBo {

    //除法暂时保留4位小数
    private int length = SYSTEM_COMMON_PRECISION;
    //控制图标签
    private String label;
    //ucl
    private double ucl;
    //lcl
    private double lcl;
    //控制图点位置
    private List<SPCPointBo> points;
    //均值
    private double average;
    //判异异常
    private List<String> errors;


    public void rule() {
        //八大判异
        errors = CollectionUtil.newArrayList(RULE_CONTEXT.executeRules(this.getPoints(), this.getAverage(), this.getUcl(), this.getLcl()));
    }


    public double getLcl() {
        return Mutil.round(this.lcl, SYSTEM_COMMON_PRECISION);
    }


    public double getUcl() {
        return Mutil.round(this.ucl, SYSTEM_COMMON_PRECISION);
    }


    public void setPoints(List<SPCPointBo> points) {
        points.forEach(point -> {
            point.setValue(Mutil.round(point.getValue(), SYSTEM_COMMON_PRECISION));
        });
        this.points = points;
    }


    public double getAverage() {
        return Mutil.round(this.average, SYSTEM_COMMON_PRECISION);

    }
}
