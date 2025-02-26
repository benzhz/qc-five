
// ----------------------------
// Collection structure for spc_chart
// ----------------------------
db.getCollection("spc_chart").drop();
db.createCollection("spc_chart");

// ----------------------------
// Documents of spc_chart
// ----------------------------
db.getCollection("spc_chart").insert([ {
    _id: NumberLong("1880984841401430016"),
    name: "根目录",
    chartCategory: "",
    chart: "",
    sampleSize: NumberInt("0"),
    _class: "com.benzhz.qcfive.entity.SpcChart"
} ]);
