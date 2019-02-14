# StatusProgressView
效果图:

![效果图](https://raw.githubusercontent.com/barryzzz/StatusProgressView/master/test.jpg?token=AK9OhjAMPumMTDmZvtR8XZZASyG1ZwY_ks5cZRTKwA%3D%3D)

使用说明：
```
    <com.lsl.statusprogressview.StatusProgressView
        android:id="@+id/statusview"
        android:layout_width="match_parent"
        android:layout_height="100dp"
        app:completeBackgroud="@drawable/jd_step_01"
        app:completeColor="@color/color_completed"
        app:completeState="2"
        app:itemHalfLine="true"
        app:itemTextStyle="Bottom"
        app:itemTextSize="12sp"
        app:itemValues="@array/ProgressStatus"
        app:lineSize="10dp"
        app:uncompleteBackgroud="@drawable/jd_step_02"
        app:uncompleteColor="@color/color_uncompleted" />
```


参数说明：

属性 | 属性说明
---- | ---
completeState | 状态位置(完成)
lineSize | 线条大小
completeBackgroud | 完成状态的图标
uncompleteBackgroud | 未完成状态图标
completeColor | 完成状态颜色
uncompleteColor | 未完成状态颜色
itemTextSize | 字体大小
itemValues | 状态值
itemHalfLine | 线条是否折半(默认不折半)
itemTextStyle | 字体位置(顶部，底部，默认底部)
