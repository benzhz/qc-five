# 项目介绍
在现代制造业中，产品质量的稳定性与一致性至关重要。统计过程控制（SPC）作为一种基于数据分析的质量控制方法，能有效监测生产过程中的变异，及时发现潜在质量问题。为满足高效精准 SPC 分析的需求，开发此工具。

| **<font style="color:rgb(64, 72, 91);">项目</font>** | gitee | **<font style="color:rgb(64, 72, 91);">github</font>** | **<font style="color:rgb(64, 72, 91);">简介</font>** |
| :--- | --- | :--- | :--- |
| **<font style="color:rgb(64, 72, 91);">qc-five</font>** | [<font style="color:rgb(0, 89, 128);">qc-five</font>](https://gitee.com/seventeen8128/qc-five) | [qc-five](https://github.com/benzhz/qc-five)**<font style="color:rgb(64, 72, 91);"></font>** | **<font style="color:rgb(64, 72, 91);">后端项目</font>** |
| qc-five-web | [<font style="color:rgb(0, 89, 128);">qc-five-web</font>](https://gitee.com/seventeen8128/qc-five-web) | [qc-five-web](https://github.com/benzhz/qc-five-web) | <font style="color:rgb(64, 72, 91);">基于vue-vben-admin v2开发的项目前端</font> |

后端项目：

**框架**

环境：JDK1.8

| <font style="color:#40485b;">框架</font> | <font style="color:#40485b;">版本</font> | <font style="color:#40485b;">说明</font> |
| --- | --- | --- |
| [<font style="color:#095eab;">spring boot</font>](https://gitee.com/link?target=https://github.com/alibaba/spring-cloud-alibaba) | <font style="color:#40485b;">2.6.13</font> | |
| [<font style="color:#095eab;">easyexcel</font>](https://gitee.com/link?target=https://github.com/alibaba/nacos) | <font style="color:#40485b;">3.2.1</font> | <font style="color:#40485b;">Excel处理工具</font> |
| [<font style="color:#095eab;">spring data mongdb</font>](https://gitee.com/link?target=https://github.com/apache/rocketmq) | <font style="color:#40485b;">2.6.13</font> | <font style="color:#40485b;">数据库工具</font> |
| [<font style="color:#095eab;">commons-math3</font>](https://gitee.com/link?target=https://github.com/alibaba/sentinel) | <font style="color:#40485b;">3.6.1</font> | <font style="color:#40485b;">科学计算类库</font> |
| <font style="color:#095eab;">mongdb</font> | <font style="color:#40485b;">v8.0.4</font> | <font style="color:#40485b;">NoSQL</font><font style="color:#40485b;">数据库</font> |


PS：mongdb部署需采用副本集模式或者分片集群模式，否则不支持事务；

**运行：**

修改为你的mongdb配置：

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740551918251-c91fe26e-571b-4f76-8dc5-47b2cc47a5b9.png)

mongdb库执行scripts/spc_chart.js这个脚本；

启动QcFiveApplication.class即可

**前端项目：**

node：v20.18.1

pnpm ：10.5.1

**安装使用**

+ **获取项目代码**

```bash
git clone https://github.com/benzhz/qc-five-web.git
```

+ **安装依赖**

```bash
cd qc-five-web
pnpm install
```

+ **运行**

```bash
pnpm serve
```

+ **打包**

```bash
pnpm build
```

vben相关文档：

[https://github.com/vbenjs/vue-vben-admin/tree/v2](https://github.com/vbenjs/vue-vben-admin/tree/v2)

[https://doc.vvbin.cn/](https://doc.vvbin.cn/)



**已完成：**

1.  SPC 8常见控制图；
2.  控制图八大判异准则；
3.  正态性检验；
4.  过程能力分析报告；

**下一步计划：**

1. **图表拓展**：引入箱线图等图表，从更多维度展示数据分布，挖掘潜在质量信息。
2. **MSA 开发**：开展测量系统分析（MSA）功能开发，评估测量系统的准确性、精确性和可靠性；
3. **机器学习算法引入**：引入如 KNN 等机器学习算法，对生产过程中的变异进行预测分析；



# SPC知识讲解
SPC是什么？

SPC 是一种用于分析和监控生产制造流程的系统方法。透过收集和分析生产各个阶段的数据，SPC 有助于识别可能发生的任何偏差或异常情况，以便及时採取纠正措施。

简单的来讲：就是判断产线生产的稳定性，分析和监控产品线生产合格产品的能力，并能提前发现可能影响产品合格的潜在因素，预防质量问题，同时辅助管理者在产线相关的各方面做决策，推动产线持续改进优化；

## <font style="color:rgb(25, 27, 31);">SPC相关的几个重要的概念</font>
### <font style="color:rgb(25, 27, 31);">（1）变差</font>
<font style="color:rgb(25, 27, 31);">测量某一个物体，常常采用“</font><font style="color:rgb(51, 51, 51);">精度±公差”的标准来判断是否合格。</font>

<font style="color:rgb(51, 51, 51);">如：40 ± 0.001 mm，此时，判定合格的范围39.901 mm至40.099 mm判定为合格；</font>

<font style="color:rgb(25, 27, 31);">因为世界上没有两张完全相同的树叶一样，任何一个工厂，无论其多么先进，从其生产线出来的同一种产品或多或少总会存在一些差异，这种</font>**<font style="color:rgb(25, 27, 31);">差异</font>**<font style="color:rgb(25, 27, 31);">就是</font>**<font style="color:rgb(25, 27, 31);">变差</font>**<font style="color:rgb(25, 27, 31);">。</font>

### <font style="color:rgb(25, 27, 31);">（2）普通原因 vs 特殊原因</font>
<font style="color:rgb(25, 27, 31);">比如：类似上面的例子，有两个产品尺寸都超出了[39.901,40.099]这个范围，但产生的原因，可能截然不同。</font>

**<font style="color:rgb(25, 27, 31);">普通原因</font>**<font style="color:rgb(25, 27, 31);">：可能是设备长期正常使用下的微小磨损累积，导致每次加工的产品尺寸都有小波动，时间长了部分产品尺寸偏差超范围；</font>

**<font style="color:rgb(25, 27, 31);">特殊原因</font>**<font style="color:rgb(25, 27, 31);">：可能是某次设备突然故障，使正在加工的产品尺寸出现较大偏差超范围；</font>

<font style="color:rgb(25, 27, 31);">这两种情况都造成了产品尺寸偏差超范围的结果，只是引发原因的性质不同；</font>

<font style="color:rgb(25, 27, 31);">SPC其神奇之处，在于它能够敏锐地捕捉到数据的变化模式。像控制图上，正常波动范围内的数据点对应的可能就是普通原因，而超出控制界限或者呈现出非随机分布的数据点，就提示我们可能存在特殊原因；</font>

### <font style="color:rgb(25, 27, 31);">（3）受控 vs 不受控</font>
<font style="color:rgb(25, 27, 31);">如果一个过程仅仅只有普通原因引起的变差，我们就说这个</font>**<font style="color:rgb(25, 27, 31);">过程受控</font>**<font style="color:rgb(25, 27, 31);">；</font>

<font style="color:rgb(25, 27, 31);">如果一个过程存在特殊原因引起的变差，我们就说这个</font>**<font style="color:rgb(25, 27, 31);">过程不受控</font>**<font style="color:rgb(25, 27, 31);">。</font>

<font style="color:rgb(25, 27, 31);">控制图的使命就是帮助我们发现并消除导致过程变异的特殊原因，这是一个使过程从不受控变成受控的过程。</font>

### <font style="color:rgb(25, 27, 31);">（4）中心极限定理</font>
<font style="color:rgb(25, 27, 31);">中心极限定理是SPC的重要理论依据；</font>

**<font style="color:rgb(25, 27, 31);">定理</font>**<font style="color:rgb(25, 27, 31);">：</font>

<font style="color:rgb(25, 27, 31);">“设X1，X2，...，Xn为n个相互独立同分布随机变量，其总体的分布未知，但其均值和方差都存在，当样本容量足够大时，样本均值的分布将趋近于正态分布”；</font>

**<font style="color:rgb(25, 27, 31);">例子</font>**<font style="color:rgb(25, 27, 31);">：</font>

<font style="color:rgb(25, 27, 31);">不管全中国的30岁男人体重成何种分布,我们随机抽N个人的重量并计算其均值,那么当N足够大的时候,那么N个人的平均重量W就会接近于成正态分布；</font>

**<font style="color:rgb(25, 27, 31);">多大算“足够大”？</font>**

<font style="color:rgb(25, 27, 31);">如果总体的分布对称，N>=5时效果就比较理想了；如果总体分布不对称，一般N>=30时候才算足够大；</font>

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740366501583-960b6241-d17e-457d-98c7-2b79bacb6dad.png)

### <font style="color:rgb(25, 27, 31);">（5）合理的抽样</font>
**<font style="color:rgb(25, 27, 31);">抽样目的</font>**<font style="color:rgb(25, 27, 31);">：从总体中抽取部分样本进行检测和分析，以样本数据推断总体质量状况，判断过程是否稳定、是否存在异常波动；抽样一方面是出于成本因素的考量，再者有的质量特性只能进行抽样研究，比如需要通过破坏性实验获得的质量数据；</font>

**<font style="color:rgb(25, 27, 31);">合理抽样：</font>**

<font style="color:rgb(25, 27, 31);">（一）样本大小（子组容量）：样本量大小会影响对总体估计的准确性和可靠性。一般来说，样本量越大，估计越准确，但抽样成本也越高，在SPC中（子组容量）会影响控制图的敏感度，样本越大能探测到的均值偏移（相对总体均值的偏移）越小；</font>

<font style="color:rgb(25, 27, 31);">（二）抽样频率：抽样频率取决于过程的稳定性和能力。过程稳定、能力充足，可适当降低抽样频率；过程不稳定或能力不足，则需增加抽样频率，及时发现过程变化；</font>

<font style="color:rgb(25, 27, 31);">（三）抽样类型：</font>

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740382553578-eade7850-82c3-44d8-9029-39feceb16aa0.png)

<font style="color:rgb(25, 27, 31);">（四）抽样计划的关键：</font>

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740545222390-afbb7249-17d5-413e-be82-237386e0f048.png)

## <font style="color:rgb(25, 27, 31);">SPC的核心要点</font>
### （1）基本原理
![](https://cdn.nlark.com/yuque/0/2025/jpeg/38711469/1740385167037-8939d4c9-1bec-4f99-9d92-64523be52ad7.jpeg)

前面说到SPC依据于中心极限定理，即<font style="color:rgb(25, 27, 31);">当样本容量足够大时，样本均值的分布将趋近于正态分布。</font>

<font style="color:rgb(25, 27, 31);">根据</font>**<font style="color:rgb(25, 27, 31);">正态分布的性质</font>**<font style="color:rgb(25, 27, 31);">我们可知：</font>

<font style="color:rgb(25, 27, 31);">1：离均值越远的数据出现频率越低；</font>

<font style="color:rgb(25, 27, 31);">2：约68.26%的数据落在均值±1倍标准差（u</font><font style="color:rgb(51, 51, 51);">±</font><font style="color:rgb(25, 27, 31);">σ）范围内；</font>

<font style="color:rgb(25, 27, 31);">3：约95.45%的数据落在均值±2倍标准差（u</font><font style="color:rgb(51, 51, 51);">±</font><font style="color:rgb(25, 27, 31);">2σ）范围内；</font>

<font style="color:rgb(25, 27, 31);">4：约99.73%的数据落在均值±3倍标准差（u</font><font style="color:rgb(51, 51, 51);">±</font><font style="color:rgb(25, 27, 31);">3σ）范围内。</font>

<font style="color:rgb(25, 27, 31);">故我们可知超出（u</font><font style="color:rgb(51, 51, 51);">±</font><font style="color:rgb(25, 27, 31);">3σ）范围的两端的概率仅有0.27%，为极小概率事件；</font>

<font style="color:rgb(25, 27, 31);">SPC在此基础上将</font>**<font style="color:rgb(25, 27, 31);">控制上限（UCL）</font>**<font style="color:rgb(25, 27, 31);">定为（u</font><font style="color:rgb(51, 51, 51);">+</font><font style="color:rgb(25, 27, 31);">3σ）位置，</font>**<font style="color:rgb(25, 27, 31);">控制下限（LCL）</font>**<font style="color:rgb(25, 27, 31);">定为（u</font><font style="color:rgb(51, 51, 51);">-</font><font style="color:rgb(25, 27, 31);">3σ）位置；</font>

<font style="color:rgb(25, 27, 31);">如果在实际生产过程中出现数据点超出</font>**<font style="color:rgb(25, 27, 31);">控制界限（u</font>****<font style="color:rgb(51, 51, 51);">±</font>****<font style="color:rgb(25, 27, 31);">3σ）</font>**<font style="color:rgb(25, 27, 31);">的情况，就有较大可能认为过程受到了特殊原因的影响，使过程失控，从而及时采取措施进行调整；</font>

<font style="color:rgb(25, 27, 31);"></font>

<font style="color:rgb(25, 27, 31);">另外同时SPC会基于时间纬度进行抽样，控制图往往会围绕着目标值（中间线）产生波动，各个时间节点的取样数据可以认为是服从正态分布的；</font>

![](https://cdn.nlark.com/yuque/0/2025/jpeg/38711469/1740383889387-f721e4f1-6c98-4b5c-b8a4-094befeec308.jpeg?x-oss-process=image%2Fformat%2Cwebp%2Finterlace%2C1)

（PS：图中上规格线、跟下规格线，它与上下控制线不一样，我们在后面的CPK部分会提到）

我们将上图随着时间流动的服从正态分布规律的数据点，从上方俯视，就完成了正态分布曲线到控制图的转换；

这就是SPC控制图：

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740386765628-6196cc91-2b0f-497d-9b72-54ff8e3bd2d8.png)

### （2）控制图
**1.总览**

| 类别 | 名称 | 符号 | 特点 | 适用场合 |
| --- | --- | --- | --- | --- |
| 计量型控制图<br/> | 均值－极差控制图 | x̄ -R图 | 最常用，使用范围广；提供信息多；判断工序是否正常的效果好，计算R值的工作量小。 | 适用于产品批量大且生产正常、稳定的工序。 |
| | 均值－标准差控制图 | x̄-S图 | 常用，判断工序是否正常的效果最好，但计算s的工作量大。 | 适用于产品批量大且生产正常、稳定的工序。 |
| | 中位数－极差控制图 | x̃-R图 | 计算简便，宜在现场采用；提供信息少，但检出能力便差。 | 适用于产品批量大且生产正常、稳定的工序。 |
| | 单值－移动极差控制图 | x-MR图 | 简便省事，能及时判别工序是否处于稳定状态。缺点是不易发现工序分布中心的变化。 | 因各种原因（时间或费用）每次只能得到一个数据或尽快发现并消除异常因素。 |
| 计数型控制图<br/> | 不合格品数控制图 | np图 | 较常用，计算简洁，作业人员易于掌握。样本含量较大。 | 样本含量相等。 |
| | 不合格品率控制图 | p图 | 样本取样量大，且计算量大，控制线凹凸不平；检出能力与样本容量n有关。 | 样本含量可以不等。 |
| | 缺陷数控制图 | c图 | 较常用，计算简洁，作业人员易于掌握，要求样本量大。 | 样本含量相等。 |
| | 单位缺陷图控制图 | u图 | 计算量大，控制曲线凹凸不平；检出能力与样本容量n有关。 | 样本含量可以不等。 |


**2.控制图的选择**

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740391035175-a03941ab-c63c-4edb-bbe8-3dfd45d3f0a3.png)

3**.控制图介绍**

**①、****x̄**** -R图（均值－极差控制图）**

我们从x̄ -R图这个最常见的控制图，开始了解SPC控制图；

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740404536597-f5c42abe-d67e-49f8-a967-7efe318f8193.png)

在上图中，我们对比抽检的样本数据组，和它对应的生成的控制图，其中控制图各个采样时间点，并不是罗列组内所有样本数据，而是先对组内样本数据求均值或者极差，再将得出的统计量放入图中；这是因为：

1.原始数据多而乱，直接使用，会导致控制图过于复杂，反而难以直观看出变化规律和整体趋势，使用统计量是一种数据浓缩和概括；

2.实际数据不可避免存在随机误差，使用统计量，可以在一定程度上弱化这些随机误差的影响，使数据更稳定；

3.每种统计量，都有其各自的作用，比如：

**均值控制图**重点关注生产过程中产品质量指标的**平均水平变化**，假设均值控制图上**连续多个采样点**超出控制上限，这可能意味着生产设备的参数发生了变化，比如充电电压升高导致电池容量普遍增大，或者是原材料的批次不同，使得电池平均容量出现了偏移，通过均值控制图及时发现这种**组间变异**；

**极差控制图**更侧重于监控产品质量指标的**离散程度变化**，假设极差控制图上的**某个时刻采样点**突然明显增大，这表明这一组电池容量之间的差异变大了。可能是生产设备的稳定性出现问题，比如焊接设备的焊接强度不稳定，导致电池内部结构存在差异，进而影响了电池容量的一致性；也可能是操作人员的操作手法不一致造成的。通过极差控制图发现这种**组内变异**；

或者假设有N个村子，调研村子的居民经济水平，均值控制图，能帮直观我们对比出不同村子平均水平差异，有没有连续相邻几个村子特别贫困，可能是地理等条件导致的。而极差控制图（组内最大值-最小值），如果有个别村子极差值特别大，这个村子也许存在巨大的贫富差异。

**②、****x̄****-S图（均值－标准差控制图）**

主要有均值控制图和标准差控制图组成，组内样本数（子组容量）需要达到10以上。

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740405900249-afdb1825-65fc-4b64-99fb-c05c1f4f1ab4.png)

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740405701432-7d01ec4a-e37d-40eb-a967-7dbcf8ed089c.png)

均值控制图作用同上；

**标准差控制图**常用于衡量产品质量的稳定性，例如，某汽车零部件生产厂家生产的零件长度要求为10厘米，为了监控生产过程，随机抽取了一批零件测量其长度。通过计算这些零件长度的标准差，可以判断生产过程的稳定性。如果标准差较小，生产过程稳定，产品质量较为一致；如果标准差较大，说明生产过程中可能存在一些因素影响了零件的精度，如设备磨损、原材料差异等，需要及时排查问题，

又例如，在体育赛事中，标准差可用于评估运动员的表现稳定性。以射击运动员为例，计算其多次射击成绩的标准差。如果标准差较小，说明该运动员每次射击的成绩较为接近，发挥比较稳定；如果标准差较大，则表示运动员的成绩波动较大，可能在比赛中存在较大的不确定性。

**  	③、****x̃****-R图（中位数－极差控制图）**

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740408305392-cdacaf04-2b99-4ffc-9dfa-f8a2cb1d1d6d.png)

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740408281309-608a5e60-a7f1-4c26-9774-512d661e2833.png)

**中位数控制图**，当数据存在**极端值**或**分布偏态**时，均值会受到极端值的较大影响，而中位数相对稳定，能更合理地代表数据的典型水平，例如，在统计某公司员工的工资时，若少数高管工资极高，这些极端高值会拉高工资的均值，使均值不能很好地代表普通员工的工资水平。而中位数不受这些极端高值的影响，能更真实地反映大多数员工工资的中间水平，也就是更能体现普通员工的工资状况这种平均水平。

中位数控制图在有很好的极端值抗性，故在存在个别极端值的情况下，它更能反应组内典型水平；但是在非极端但整体变化的情况下，中位数的反应则较为迟缓一些；

举个例子：比如有个销售团队，我们分别取这个均值和中位数来代表团队月销售情况，如果中数位为连续3月逐月下降100元的销售额，我们会认为这个可能是正常波动，而换成均值逐月下降100元，下降额是100x团队人数，我们则会更多感觉团队在走下坡路，要及时调整；

**④、****x****-MR图 （单值－移动极差控制图）**

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740458707171-cad0d83f-e6eb-420c-aacc-4cc49af7c3bb.png)

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740490967864-2b972437-1ac9-414e-a193-27cf175db726.png)

**单值控制图**是在不同时刻分别抽取一个数据点进行分析，这些单值数据点按时间顺序排列展示在控制图上，以此来监控过程随时间的变化情况，观察过程的均值是否稳定，有没有异常波动等。例如在连续监测设备的运行参数时，**每隔一段时间记录一次参数值，每个参数值就是一个单值，用于判断设备运行状态是否正常**。

**移动极差控制图**<font style="color:rgba(0, 0, 0, 0.85);">，移动极差较大，说明数据点之间的差异较大，数据较为离散；反之，移动极差较小则表示数据相对集中，离散程度较小。例如在生产过程中，如果产品质量特性值的移动极差较大，意味着不同批次或不同时间段生产的产品质量差异较大，可能存在影响生产过程稳定性的因素。若移动极差呈现出稳定的状态，即在控制限内随机波动，说明生产过程的短期波动处于受控状态，生产过程相对稳定；若移动极差出现连续上升或下降趋势、周期性变化，或者超出控制限等异常情况，则表明生产过程存在特殊原因导致的短期波动异常，需要及时查找原因并采取措施进行调整</font>

**适用场景：**

+ **数据无法分组或数据量较少**：比如小批量或单件生产过程中的质量控制，像定制化产品的生产，每次生产的数量很少，难以进行分组。
+ **连续数据**：适用于对连续型数据的监控，例如化工生产过程中的温度、压力等参数的监测，这些参数是连续变化的。

**⑤、np图 （不合格品数控制图）**

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740491018835-8e4b18d9-8610-4d4b-a599-93bbf98d9dc3.png)

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740491044441-91af4964-8850-48c3-8f14-d50ec32312ee.png)

**不合格品数控制图**<font style="color:rgba(0, 0, 0, 0.85);">用于监控生产过程中</font>**<font style="color:rgba(0, 0, 0, 0.85);">不合格品数量</font>**<font style="color:rgba(0, 0, 0, 0.85);">变化的一种控制图，例如，在电子元件生产中，对一批固定数量的元件进行检验，判断其是否符合规格要求，就可以使用不合格品数控制图来监控生产过程的稳定性。</font><font style="color:#DF2A3F;">注意：每批抽样的样本总数量要相同，不然计算会有问题</font><font style="color:rgba(0, 0, 0, 0.85);">；</font>

<font style="color:rgba(0, 0, 0, 0.85);">原理：当每个样本的单位数（样本数）固定，且每个单位是否合格是独立事件时，不合格品数服从二项分布，当样本量足够大时，二项分布可近似为正态分布。</font>

**⑥、p图 （不合格品率控制图）**

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740491195638-865cd0ae-3311-408c-9498-4a1ef4d632ad.png)

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740491267501-e56c36fa-e8ed-47ab-8fed-93d7f1dee531.png)

**不合格品率控制图**<font style="color:rgba(0, 0, 0, 0.85);">用于监控生产过程中产品</font>**<font style="color:rgba(0, 0, 0, 0.85);">不合格品率</font>**<font style="color:rgba(0, 0, 0, 0.85);">变化的统计工具，适用于</font>**<font style="color:rgba(0, 0, 0, 0.85);">样本量不固定</font>**<font style="color:rgba(0, 0, 0, 0.85);">，但可将产品明确区分为合格品与不合格品的生产过程。例如，在不同批次产品数量不同的组装生产线中，通过检验各批次产品的不合格品率来监控生产过程的稳定性</font>

<font style="color:rgba(0, 0, 0, 0.85);">原理：在样本抽样中，不合格品数服从二项分布。当样本量足够大时，根据中心极限定理，样本不合格品率近似服从正态分布。</font>

**⑦、c图 （缺陷数控制图）**

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740491380036-ab9d3339-20ae-44c2-aa29-c1c2aee430e7.png)

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740491400318-b8e967ff-0620-4211-aa1c-02641b9d0666.png)

**缺陷数控制图**<font style="color:rgba(0, 0, 0, 0.85);">用于控制生产过程中单位产品缺陷数的一种控制图，</font>适用于对产品表面缺陷、铸件的砂眼、电路板上的焊接不良点等情况的监控，即产品的缺陷可以被逐一计数，且单位产品的缺陷数相对较少，同时缺陷出现的概率相对稳定的生产过程。

抽样前要根据产品性质，<font style="color:rgba(0, 0, 0, 0.85);">明确样本的单位和抽样频率。例如，确定以每平方米的布料或每 10 个产品为一个样本单位，每隔一定时间抽取一个样本。每次抽取的</font>**<font style="color:rgba(0, 0, 0, 0.85);">样本量固定</font>**

原理：<font style="color:rgba(0, 0, 0, 0.85);">一定单位产品（样本数固定）中出现的缺陷数服从参数为的泊松分布，即当样本量较大时，泊松分布近似正态分布。</font>

**⑧、u图 （单位缺陷图控制图）**

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740491479851-059901f9-fa8c-40e9-9e10-1794ec2db580.png)

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740491468680-30f611c6-12bb-4715-b745-1e553170e891.png)

**<font style="color:rgba(0, 0, 0, 0.85);">单位缺陷数控制图</font>**<font style="color:rgba(0, 0, 0, 0.85);">是用于监控生产过程中单位产品平均缺陷数的统计控制图，例如，在电子元器件生产中，对不同批次、不同数量的产品进行表面缺陷检查；在纺织行业中，对不同长度的布匹进行瑕疵检验等场景，都可以使用单位缺陷数控制图来监控生产过程的稳定性</font>

<font style="color:rgba(0, 0, 0, 0.85);">原理：总缺陷数（单位产品的平均缺陷数x样本量）服从泊松分布，即当样本量足够大时，泊松分布近似正态分布。</font>

### （3）控制图判异
前面我们提到SPC控制图，如果超出UCL、LCL控制线则判定为异常，但实际情况SPC的判异不仅仅如此，SPC控制图的判异与体检的心电图有点类似，**除了观测范围区间，点的波动的观测也非常重要**，接下来我将介绍SPC的八大判异准则。

**一、控制图的分区**

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740463867003-8281b1b4-4092-4180-b536-99f7d110ac39.png)

SPC控制图会根据距离正态分布中心位置的远近分出C、B、A三个分区：

u：期望，<font style="color:rgb(25, 27, 31);">σ：标准差</font>

C区：<font style="color:rgb(25, 27, 31);">数据落在（u</font><font style="color:rgb(51, 51, 51);">-</font><font style="color:rgb(25, 27, 31);">σ,u+σ）范围内;</font>

<font style="color:rgb(25, 27, 31);">C区：数据落在（u</font><font style="color:rgb(51, 51, 51);">-2</font><font style="color:rgb(25, 27, 31);">σ,u</font><font style="color:rgb(51, 51, 51);">-</font><font style="color:rgb(25, 27, 31);">σ）或（u</font><font style="color:rgb(51, 51, 51);">+</font><font style="color:rgb(25, 27, 31);">σ,u</font><font style="color:rgb(51, 51, 51);">+2</font><font style="color:rgb(25, 27, 31);">σ）范围内;</font>

<font style="color:rgb(25, 27, 31);">A区：数据落在（u</font><font style="color:rgb(51, 51, 51);">-3</font><font style="color:rgb(25, 27, 31);">σ,u</font><font style="color:rgb(51, 51, 51);">-2</font><font style="color:rgb(25, 27, 31);">σ）或（u</font><font style="color:rgb(51, 51, 51);">+2</font><font style="color:rgb(25, 27, 31);">σ,u</font><font style="color:rgb(51, 51, 51);">+3</font><font style="color:rgb(25, 27, 31);">σ）范围内;</font>

<font style="color:rgb(25, 27, 31);">UCL：u</font><font style="color:rgb(51, 51, 51);">+3</font><font style="color:rgb(25, 27, 31);">σ</font>

<font style="color:rgb(25, 27, 31);">LCL： u</font><font style="color:rgb(51, 51, 51);">-3</font><font style="color:rgb(25, 27, 31);">σ</font>

**<font style="color:rgb(25, 27, 31);">二、判异准则</font>**

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740464491282-1ceef6b0-1067-4d49-ac29-461592614042.png)

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740464549167-5376ae66-ea34-4baa-a202-254100943bbf.png)

**<font style="color:rgb(25, 27, 31);">①、</font>****<font style="color:rgb(25, 25, 25);">1界外（1点落在A区以外）</font>**

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740465031780-68bb386a-c998-4b2d-9db0-2dd549f1871f.png)

**<font style="color:rgb(25, 25, 25);">解释：</font>**<font style="color:rgb(25, 25, 25);">正态分布中出现在ABC分区以外的概率是0，但现在发生了，小概率事件产生了，肯定有特殊原因导致！</font>

**<font style="color:rgb(25, 25, 25);">原因：</font>**<font style="color:rgb(25, 25, 25);">一般认为是新员工，工艺方法错误，机器故障，原材料不合格，测量错误，计算错误，检验方法或标准变化。</font>

**<font style="color:rgb(25, 25, 25);">②、9单侧（连续9点落在中心线同一侧）</font>**

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740465153542-75174c8b-8a3d-46ef-807c-98d6edc26a63.png)

**<font style="color:rgb(25, 25, 25);">解释：</font>**<font style="color:rgb(25, 25, 25);">概率（0.341+0.136+0.023）的9次方=0.00195， 就是说这种情况出现的概率是百分之二，小概率事件产生了，肯定有特殊原因导致！</font>

**<font style="color:rgb(25, 25, 25);">原因：</font>**<font style="color:rgb(25, 25, 25);">一般认为是新员工，工艺方法错误，机器故障，原材料不合格，测量错误，计算错误，检验方法或标准变化。</font>

**<font style="color:rgb(25, 25, 25);">③、6连串（连续6点递增或递减，即连成一串）</font>**

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740465280304-e84899a5-f17e-4b0e-8c64-55c4719b25ab.png)

**<font style="color:rgb(25, 25, 25);">解释：</font>**<font style="color:rgb(25, 25, 25);">规律分布，必有异因！</font>

**<font style="color:rgb(25, 25, 25);">原因：</font>**<font style="color:rgb(25, 25, 25);">刀具模具等工具的磨损，维护保养水平降低，操作工的技能越来越熟练。</font>

**<font style="color:rgb(25, 25, 25);">④、14交替（连续14点相邻点上下交替）</font>**

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740465338402-ad15346f-da35-4389-99e7-cb81fa354ecc.png)

**<font style="color:rgb(25, 25, 25);">解释：</font>**<font style="color:rgb(25, 25, 25);">规律分布，必有异因！</font>

**<font style="color:rgb(25, 25, 25);">原因：</font>**<font style="color:rgb(25, 25, 25);">轮流使用两台设备或两个操作工操作，分别针对两个模穴定期抽样但一起统计等导致数据分层不够。</font>

<font style="color:rgb(25, 25, 25);">⑤</font>**<font style="color:rgb(25, 25, 25);">、2/3A（连续3点中有2点在中心线同一侧的B区外<即A区内>）</font>**

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740465397349-4abe4ca0-8a14-461c-9e0d-a0c29b769188.png)

**⑥、****<font style="color:rgb(25, 25, 25);">4/5C（连续5点中有4点在中心线同一侧的C区以外）</font>**

  
 ![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740465453591-c1f52f29-3e1a-4834-8cfd-023627b742e6.png)

**<font style="color:rgb(25, 25, 25);">解释：</font>**<font style="color:rgb(25, 25, 25);">概率  （0.136+0.023）的4次方=0.000639，就是说这种情况出现的概率是千分之六，小概率事件产生了，肯定有特殊原因导致！</font>

**<font style="color:rgb(25, 25, 25);">原因：</font>**<font style="color:rgb(25, 25, 25);">一般认为是新员工，工艺方法错误，机器故障，原材料不合格，测量错误，计算错误，检验方法或标准变化。</font>

**<font style="color:rgb(25, 25, 25);">⑦、15全C（连续15点在C区中心线上下，即全部在C区内）</font>**

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740465592408-c3b15303-b8d7-4081-ade4-e0c529c72f5c.png)

**<font style="color:rgb(25, 25, 25);">解释：</font>**<font style="color:rgb(25, 25, 25);">概率 （0.341+0.341）的15次方=0.00321，就是说这种情况出现的概率是千分之三，小概率事件产生了，肯定有特殊原因导致！</font>

**<font style="color:rgb(25, 25, 25);">原因：</font>**<font style="color:rgb(25, 25, 25);">数据造假，控制限计算错误太宽了，数据分层不够。</font>

**<font style="color:rgb(25, 25, 25);">⑧、8缺C（连续8点在中心线两侧，但没有一点在C区中）</font>**

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740465619141-ff1e68cb-1463-4511-a141-6947d8296902.png)

**<font style="color:rgb(25, 25, 25);">解释：</font>**<font style="color:rgb(25, 25, 25);">概率（0.023+0.136+0.136+0.023）的8次方=0.000105，就是说这种情况出现的概率是万分之一，小概率事件产生了，肯定有特殊原因导致！</font>

**<font style="color:rgb(25, 25, 25);">原因：</font>**<font style="color:rgb(25, 25, 25);">数据分层不够（SPC没有分模穴去分开做，没有分班次去做等等）</font>

### （4）正态性检验
SPC控制图以及控制图的判异，都是基于数据独立同分布，服从正态分布这个大前提的，所以正态性检验也是检验SPC控制图有效的重要工具；

什么是数据不独立同分布?

例如：

1、在一个自动化的机械加工设备中，设备会根据上一批次零件加工的尺寸偏差数据来自动调整刀具的位置、切削速度等参数。那么下一批次零件加工的数据（如尺寸、精度等）就和上一批次的数据以及参数调整密切相关，上一批次的数据会影响到设备参数的设置，进而影响下一批次的数据，所以不同批次生产的数据之间存在关联，不满足独立性的要求；

2、服装工厂生产某款服装，在生产初期，由于工人对新款服装的制作工艺还不够熟练，生产出来的服装尺寸偏差较大。随着工人熟练度的提高，后期生产的服装尺寸更接近标准尺寸，尺寸数据的分布发生了变化，即不是同分布的。而且，同一班组连续生产的几件服装，由于是同一工人操作，尺寸数据可能会受到该工人操作习惯的影响，存在一定的相关性，不是相互独立的；

**正态概率图：**

正态概率图绘制流程：

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740478402608-c1bd88ba-a63e-4e3c-b23f-a49bf815c64d.png)

**分位数：**

以下图四分位数为例：

第一分位数：即整体数据有25%落在此小于分数位的区间，而75%落在此大于分数位的区间；

第二分位数：即整体数据有50%落在此小于分数位的区间，而50%落在此大于分数位的区间；

第三分位数：即整体数据有75%落在此小于分数位的区间，而25%落在此大于分数位的区间；

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740476527603-ecf1e0ba-9a6f-424c-8947-fdab10882bfb.png)

**正态概率的分位数：**

**CDF**

<font style="color:rgba(0, 0, 0, 0.85);">“CDF” 累积分布函数（Cumulative Distribution Function） ，用于描述随机变量小于或等于某个值的概率。</font>

<font style="color:rgba(0, 0, 0, 0.85);">比如下图，某物体重量11.5的CDF就是0.023，代表该物体≤11.5重的概率是0.023；</font>

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740479061302-d214095b-36b8-496b-9e68-4fb54654a266.png)

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740477579124-882a2743-ddd6-4386-a8ac-d5dffc6234b4.png)

如上图所示，整个正态分布被划分为10个区间9个位数（注意这里是用概率来划分的，而不是x轴均等分，正态的概率等于x轴的区间与概率密度曲线围成的面积）。

要计算各分位数所在位置的理论值，要先计算理论CDF，再由理论CDF跟正态分布计算出分位数所在理论值；早期分位数CDF计算采用![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740478868781-57b0ad33-3157-4650-a610-d9d40f2ba354.png)<font style="color:rgba(0, 0, 0, 0.85);"> ，后来统计学家出于种种考虑，加入了各种偏移量，常见SPC工具通常使用</font>![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740478755670-8f84683f-06d7-4d8d-95f3-c0dc599dff00.png)<font style="color:rgba(0, 0, 0, 0.85);">或者</font>![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740478768101-b1ab60c7-2d69-4f1a-9060-7602d22d477a.png)<font style="color:rgba(0, 0, 0, 0.85);">；分成9个分位数，按原先分位数的公式</font>![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740478868781-57b0ad33-3157-4650-a610-d9d40f2ba354.png?x-oss-process=image%2Fformat%2Cwebp)<font style="color:rgba(0, 0, 0, 0.85);">，1分位的CDF理应是10%，经过公式</font>![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740478768101-b1ab60c7-2d69-4f1a-9060-7602d22d477a.png)<font style="color:rgba(0, 0, 0, 0.85);">修正，1分位的CDF变成了7.45%</font>

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740479788517-c1ea1a83-fd6b-4d2d-b2ef-89b4a494ba72.png)

蓝色点是各分位数理论CDF的实际值，而直线则是理论CDF-理论值；

正态概率图可以让我直观的看到样本数据与正态分布偏离程度，但它没有量化指标，没有一个数据指标能断言，是否服从正态分布；

（PS:正态概率图这部分不清楚的话，可以看下B战大佬的课 [https://www.bilibili.com/video/BV1HJ411j7XZ/](https://www.bilibili.com/video/BV1HJ411j7XZ/?spm_id_from=333.1007.top_right_bar_window_history.content.click&vd_source=374159c2fde6f2340c6ab4d00e483b0f)）

要断言数据样本是否服从正态分布，我们使用了几种常见的检验方法：

Shapiro-Wilk检验

Kolmogorov-Smirnov检验

Anderson-Darling检验

D'Agostino-Pearson Omnibus 检验

以上检验方式，计算的算法各不相同，它们最终都会算出P值，当p值大于0.05时，无法拒绝数据服从正态分布的原假设；p值小于等于0.05时，拒绝原假设，认为数据不服从正态分布。

### （5）过程能力分析（CPK）
**精度与准度：**

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740483316278-d44a6b79-5950-4454-9610-a91aebc76654.png)

<font style="color:rgb(25, 25, 25);">例子：用一把枪瞄准靶心射击多发子弹，就会出现四种情况：</font>

<font style="color:rgb(25, 25, 25);">A、低准度低精度：表现是子弹乱飞，大部分脱离标准点，同时分布毫无规律。</font>

<font style="color:rgb(25, 25, 25);">B、低准度高精度：表现则是子弹很难打中瞄准点，但子弹的落点十分集中</font>

<font style="color:rgb(25, 25, 25);">C、高准度低精度：表现是子弹落在瞄准点周围，但子弹散布较大</font>

<font style="color:rgb(25, 25, 25);">D、高准度高精度：在这种情况下子弹上靶点，同时子弹的散布也十分集中。</font>

**<font style="color:rgb(25, 27, 31);">过程受控不一定符合设计规范：</font>**

<font style="color:rgb(25, 27, 31);">我们之前做的控制图以及八大控制图的判异，其实都在做一件事，找出特殊原因->消除特殊原因->使得过程受控，让数据更集中，但过程受控不一定符合设计规范；</font>

<font style="color:rgb(25, 27, 31);">换句话说，SPC控制精度，放在打靶例子，来说就是比较集中，但它不一定准；</font>

<font style="color:rgb(25, 27, 31);">所以我们需要</font><font style="color:rgb(51, 51, 51);">CPK控制准度，让我们的产品更接近目标值；又精又准，产品质量才会高；</font>

**<font style="color:rgb(51, 51, 51);">Cpk:</font>**

**<font style="color:rgb(51, 51, 51);">PS: 注意区分这四个，不要搞混；</font>**

+ <font style="color:rgb(51, 51, 51);">上规格限（USL）：Upper Specification Line；代表规格/标准上限</font>
+ <font style="color:rgb(51, 51, 51);">下规格限（USL）：Upper SpecificationLine；代表规格/标准下限</font>
+ <font style="color:rgb(51, 51, 51);">上控制限（UCL）：Upper Control Line；代表数据控制的上限：</font><font style="color:#DF2A3F;">+</font><font style="color:#DF2A3F;">3σ</font>
+ <font style="color:rgb(51, 51, 51);">下控制限（LCL） ：Lower Control Line；代表数据控制的下限：</font><font style="color:#DF2A3F;">-</font><font style="color:#DF2A3F;">3σ</font>

**①、Cp（制程精密度）：**

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740485700106-8e250035-8c86-4d68-b6c7-ecfadf681920.png)

如上图，Cp就是将规格上下限范围/控制区间，判断我们产品能否大概率符合客户标准；

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740485779841-af1a392b-8ea0-4d1b-9ccb-e07102638304.png)

**②、Cpk（制程能力指数）**

有时候Cp计算出的数值很优秀，但实际情况却是，数据分布偏离了中间的目标值（如下图）；所以我们需要Cpk

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740486034980-866d86ad-f39e-49a2-a758-3a1701232164.png)

Cpk公式：

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740487225956-fba722c1-8a1e-47e9-a180-57ce0cd6e491.png)

(mean：均值，正态中间值)

Cpk分为双边，分别计算上规格到均值，还有均值到下规格<font style="color:#000000;">/3σ，取小者；</font>

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740487262589-9280e987-5146-4289-84f8-50e3b32f4f46.png)

**③、Ca（制程准确度）**

Cpk能计算出制程能否符合客户要求，还有数据分布会不会偏离规格中心；而Ca能计算规格中心到实际中心（正态分布中心）的偏移程度；

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740488737238-245f81e8-b333-40cd-a8c1-e259f7a7c180.png)

C为规格中心，x̄ 实际中心；

**④、数据的收集**

<font style="color:rgb(25, 27, 31);">Cpk 的计算依据是来自于收集的收据，所以数据的收集及正确是计算Cpk的根本，通常数据收集有以下观点:</font>

+ <font style="color:rgb(25, 27, 31);">不能少于25组(个)数据，样本量的大小主要是其代表性问题，最小限量的样本量以能代表过程偏差为准；</font>
+ <font style="color:rgb(25, 27, 31);">组内和组间：组内一般为3-5个取样，习惯上取3或者5没有见过4个，以体现R极差，我们希望组内体现出特殊原因;组间一般为天数或小时，可以为25天或者整个月</font>
+ <font style="color:rgb(25, 27, 31);">数量越多越具有代表性</font>

### （6）附录
SPC控制图公式：

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740569273163-ea6d07f5-cef9-4189-ac94-accaf3179b7c.png)

SPC公式常量表![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740569386331-6b0ec796-998d-4a48-896e-f187cf462fb0.png)



# 功能与演示
以下以x̄ -R图（均值－极差控制图）为例，其它控制图步骤相同；

如何创建控制图？

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740546439429-4f86b382-c92f-4cc9-87ef-d41af4492fd3.png)

①、填写控制图信息

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740546573911-1cd5ee64-a8fc-46ba-8467-f9b37a114e70.png)

②、excel模板下载（每种控制图以及子组容量不同的控制图，生成的模板都不一样）

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740550294255-a0ff692e-ea9e-460c-9f18-fcd652f77f90.png)

excel模板例：

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740552670767-ac166fa4-2774-4742-bbd7-30e4cf0dd2c7.png)

③、填写样本数据后，点击导入样本数据

自动生成控制图

均值图

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740554224094-0de62789-203a-45ff-9037-af7461f74d22.png)

极差图



![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740554262382-59a4f192-0bcf-4c88-828f-57a7fffad007.png)

正态性检验

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740569607247-2db3bf3e-eac6-48b4-8d25-95c9aa125928.png)

过程能力报告

![](https://cdn.nlark.com/yuque/0/2025/png/38711469/1740569461320-9ba96af4-e5e9-457e-a43a-799c943c1517.png)



### <font style="color:rgb(25, 27, 31);">  
</font><font style="color:rgb(25, 27, 31);"> </font>
