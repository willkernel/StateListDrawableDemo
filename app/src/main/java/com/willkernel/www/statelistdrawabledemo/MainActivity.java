/**API：http://www.android-doc.com/reference/android/graphics/drawable/StateListDrawable.html
android:state_enabled 触摸或点击事件是否可用
android:state_pressed 是否按压状态
android:state_selected 是否选中状态
android:state_checked 是否勾选状态
android:state_checkable 勾选是否可用状态
android:state_focused 是否获得焦点状态  手机上面一般是和selected一起的，windows上面不是同样的
android:state_window_focused 当前窗口是否获得焦点状态
android:state_activated 是否被激活状态
android:state_hovered 是否鼠标在上面滑动的状态

mStateSet:维护状态Drawable对应信息，map集合，具体状态的id和drawable对应
addStateSet():添加状态与Drawable对应信息
isStateful():是否状态相关
setState():View状态变化是调用

<selector> 根元素，enterFadeDuration、exitFadeDuration
<item>  android:drawable, android:state_***=["true" | "false"]

2个相关类：ColorStateList和StateListAnimator
在ListView里使用的注意点
 在ListView的Item里设置StateListDrawable有2个地方：
 android:listSelector，但是默认背景是透明的
 ItemView里设置android:background
 checkbox ,button 焦点的获取问题
 属性 一般选择第三个，改变itemView背景
 焦点问题，用android:descendantFocusability属性：
 beforeDescendants：ListView优先子控件而获得焦点
 afterDescendants：ListVIew只有当其子控件不需要焦点时才获得焦点
 blocksDescendants：ListView会覆盖子控件而直接获得焦点

注意事项
item从上往下匹配，匹配到第一个item就采用该item
selector作为color资源时，item指定android:color属性，并放于color目录下，color目录只能用android:color
android:drawable也可以引用@color颜色值

 AnimatedStateListDrawable
 <animated-selector>
    <item> android:id,android:drawable, android:state_***=["true" | "false"]
    <transition> 设置状态切换时的动画，帧动画或向量动画
 */
package com.willkernel.www.statelistdrawabledemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements CompoundButton.OnCheckedChangeListener {
    private TextView txt;
    private CheckBox checkbox;
    private EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) this.findViewById(R.id.txt);
        edit = (EditText) this.findViewById(R.id.edit);
        checkbox = (CheckBox) this.findViewById(R.id.checkbox);

        ((CheckBox) findViewById(R.id.enabled)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.selected)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.focused)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.activated)).setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.enabled:
                txt.setEnabled(isChecked);
                break;
            case R.id.selected:
                txt.setSelected(isChecked);
                break;

            case R.id.focused:
                if (isChecked) {
                    edit.requestFocus();
                } else {
                    edit.clearFocus();
                }

                break;

            case R.id.activated:
                txt.setActivated(isChecked);
                break;

        }
    }
}
