package com.benzhz.qcfive.listener;

import cn.hutool.core.map.MapUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Cell;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.util.ConverterUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.benzhz.qcfive.common.constants.CommonConstants.pattern;
import static com.benzhz.qcfive.utils.AssertUtil.isMatch;

/**
 * excel解析成map
 * @Author：zhz
 * @Package：com.benzhz.qcfive.listener
 * @Project：qc-five
 * @name：SimpleDataListener
 * @Date：2025/2/16 22:15
 * @Filename：SimpleDataListener
 */
@Deprecated
public class SimpleDataListener extends AnalysisEventListener<Map<String,String>> {


    private Map<Integer, String> sampleHeadStringMap;



    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        Map<Integer, String> headStringMap = ConverterUtils.convertToStringMap(headMap, context);
        sampleHeadStringMap = MapUtil.filter(headStringMap, t -> isMatch(t.getValue(),pattern));
        super.invokeHead(headMap, context);
    }

    @Override
    public void invoke(Map<String, String> integerStringMap, AnalysisContext context) {
        Map<String, Object> sampleDataMap = new LinkedHashMap<String, Object>();
        Map<Integer, Cell> cellMap = context.readRowHolder().getCellMap();
        List<Double> samples = new ArrayList<>(sampleDataMap.size());
        for (Integer columnIndex : sampleHeadStringMap.keySet()) {
            ReadCellData cellData = (ReadCellData) cellMap.get(columnIndex);
            samples.add(cellData.getOriginalNumberValue().doubleValue());
        }
        //integerStringMap.setSamples(samples);
    }
}
