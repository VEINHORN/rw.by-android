package com.veinhorn.rwbytickets.search.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

/**
 * Created by veinhorn on 28.3.16.
 * This code is provided by https://github.com/makovkastar
 * This class is used for make delay between user typed text and service send some request to server
 */
public class DelayAutoCompleteTextView extends AutoCompleteTextView {
    private static final int MESSAGE_TEXT_CHANGED = 100;
    private static final int DEFAULT_AUTOCOMPLETE_DELAY = 750;

    private int mAutoCompleteDelay = DEFAULT_AUTOCOMPLETE_DELAY;

    public DelayAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private final Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            DelayAutoCompleteTextView.super.performFiltering((CharSequence) msg.obj, msg.arg1);
        }
    };

    @Override protected void performFiltering(CharSequence text, int keyCode) {
        mHandler.removeMessages(MESSAGE_TEXT_CHANGED);
        mHandler.sendMessageDelayed(
                mHandler.obtainMessage(MESSAGE_TEXT_CHANGED, text), mAutoCompleteDelay);
    }

    // By the way we don't need this method only if we want to create some progress bar
    @Override public void onFilterComplete(int count) {
        super.onFilterComplete(count);
    }
}
