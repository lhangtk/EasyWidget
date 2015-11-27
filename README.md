# EasyWidget
目前添加了两个控件：</br>
1、圆形进度条（CircleProgressBar）</br>
2、波浪形进度视图（WaveView）</br>

#CircleProgressBar
通过drawArc方式实现</br>
1 进度只能设置1以内的浮点数数据</br>
2 可以设置进度条的绘制样式，填充区域或区域边框</br>
3 在边框样式下可设置边框宽度</br>
4 可设置是否使用圆心</br>
![image](https://github.com/lhangtk/EasyWidget/blob/master/image/1.png)<br>
#WaveView
通过正弦函数实现了简单波浪效果，通过设置进度（progress）更改波浪高度</br>
可设置振幅、周期、移动速度、前景色、背景色、前景画笔、背景画笔</br>
