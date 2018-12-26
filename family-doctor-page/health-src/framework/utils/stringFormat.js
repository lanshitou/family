/**
 * [StringFormat description] 字符串格式化
 * 例子：let s = utils.StringFormat('{1}+{0}', 'a', 'b') // b+a
 * 例子：let s = utils.StringFormat('{0}+{1}', 'a', 'b') // a+b
 * 例子：let s = utils.StringFormat('【{0}】{1}', 'a', 'b') // 【a】+b
 */
// import $ from 'jquery'
const StringFormat = function (data, type, isStack) {
  // let charttype = 'line'
  // if (type) {
  //   charttype = type || 'line'
  // }
  let xAxis = []
  let series = []
  for (let i = 0; i < data.length; i++) {
    for (var j = 0; j < xAxis.length && xAxis[j] !== data[i].name; j++);
    if (j === xAxis.length) {
      xAxis.push(data[i].name)
    }
    for (var k = 0; k < series.length && series[k] !== data[i].value; k++);
    if (k === series.length) {
      series.push(data[i].value)
    }
  }
  return {
    xAxis: xAxis,
    series: series
  }
}
export default StringFormat
