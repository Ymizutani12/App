package com.example.namebattle;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.RadioButton;

public class CustomItemView extends FrameLayout implements Checkable {

    private RadioButton mRadioButton;

    public CustomItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    public CustomItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public CustomItemView(Context context) {
        super(context);
        initialize();
    }

    private void initialize() {
        // レイアウトを追加する
        addView(inflate(getContext(), R.layout.activity_custum_item_view, null));
        mRadioButton = findViewById(R.id.radioButton);
    }

    @Override
    public boolean isChecked() {
        return mRadioButton.isChecked();
    }

    @Override
    public void setChecked(boolean checked) {
        // RadioButton の表示を切り替える
        mRadioButton.setChecked(checked);
    }

    @Override
    public void toggle() {
    }

}