package com.android.credassignment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class MultiSheetView extends CoordinatorLayout {
    private static final String TAG = "MultiSheetView";

    @interface Sheet {
        int NONE = 0;
        int FIRST = 1;
        int SECOND = 2;
        int THIRD = 3;
    }


    private LockableBottomSheetBehavior bottomSheetBehavior1;
    private LockableBottomSheetBehavior bottomSheetBehavior2;
    private LockableBottomSheetBehavior bottomSheetBehavior3;

    public MultiSheetView(Context context) {
        this(context, null);
    }

    public MultiSheetView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiSheetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        View sheet1 = findViewById(R.id.sheet1);
        bottomSheetBehavior1 = (LockableBottomSheetBehavior) BottomSheetBehavior.from(sheet1);
        bottomSheetBehavior1.setLocked(true);

        View sheet2 = findViewById(R.id.sheet2);
        bottomSheetBehavior2 = (LockableBottomSheetBehavior) BottomSheetBehavior.from(sheet2);
        bottomSheetBehavior2.setLocked(true);

        View sheet3 = findViewById(R.id.sheet3);
        bottomSheetBehavior3 = (LockableBottomSheetBehavior) BottomSheetBehavior.from(sheet3);
        bottomSheetBehavior3.setLocked(true);

        //First sheet view click listener
        findViewById(getSheet1PeekViewResId()).setOnClickListener(v -> {
            if (bottomSheetBehavior1.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else if (getCurrentSheet() != Sheet.FIRST) {
                showSheet(Sheet.FIRST);
            } else {
                bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        //Second sheet view click listener
        findViewById(getSheet2PeekViewResId()).setOnClickListener(v -> {
            if (bottomSheetBehavior2.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior2.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else if (getCurrentSheet() != Sheet.SECOND) {
                showSheet(Sheet.SECOND);
            } else {
                bottomSheetBehavior2.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        //Third sheet view click listener
        findViewById(getSheet3PeekViewResId()).setOnClickListener(v -> {
            if (bottomSheetBehavior3.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior3.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else if (getCurrentSheet() != Sheet.THIRD) {
                showSheet(Sheet.THIRD);
            } else {
                bottomSheetBehavior3.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
    }

    @Sheet
    public int getCurrentSheet() {
        if (bottomSheetBehavior3.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            return Sheet.THIRD;
        } else if (bottomSheetBehavior2.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            return Sheet.SECOND;
        } else if (bottomSheetBehavior1.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            return Sheet.FIRST;
        } else {
            return Sheet.NONE;
        }
    }

    public void showSheet(@Sheet int sheet) {

        // if we are already at our target sheet, then don't do anything
        if (sheet == getCurrentSheet()) {
            return;
        }

        switch (sheet) {
            case Sheet.NONE:
                bottomSheetBehavior2.setState(BottomSheetBehavior.STATE_COLLAPSED);
                bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_COLLAPSED);
                bottomSheetBehavior3.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case Sheet.FIRST:
                bottomSheetBehavior2.setState(BottomSheetBehavior.STATE_COLLAPSED);
                bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);
                bottomSheetBehavior3.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case Sheet.SECOND:
                bottomSheetBehavior2.setState(BottomSheetBehavior.STATE_EXPANDED);
                bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);
                bottomSheetBehavior3.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case Sheet.THIRD:
                bottomSheetBehavior3.setState(BottomSheetBehavior.STATE_EXPANDED);
                bottomSheetBehavior2.setState(BottomSheetBehavior.STATE_EXPANDED);
                bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
        }
    }


    public boolean consumeBackPress() {
        switch (getCurrentSheet()) {
            case Sheet.THIRD:
                showSheet(Sheet.SECOND);
                return true;
            case Sheet.SECOND:
                showSheet(Sheet.FIRST);
                return true;
            case Sheet.FIRST:
                showSheet(Sheet.NONE);
                return true;
            case Sheet.NONE:
                break;
        }
        return false;
    }


    @IdRes
    public int getMainContainerResId() {
        return R.id.mainContainer;
    }

    @IdRes
    public int getSheet1ContainerResId() {
        return R.id.sheet1Container;
    }

    @IdRes
    public int getSheet2ContainerResId() {
        return R.id.sheet2Container;
    }

    @IdRes
    public int getSheet1PeekViewResId() {
        return R.id.sheet1PeekView;
    }

    @IdRes
    public int getSheet2PeekViewResId() {
        return R.id.sheet2PeekView;
    }

    @IdRes
    public int getSheet3PeekViewResId() {
        return R.id.sheet3PeekView;
    }

}
