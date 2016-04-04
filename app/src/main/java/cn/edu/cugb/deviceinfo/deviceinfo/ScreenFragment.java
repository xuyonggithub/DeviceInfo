package cn.edu.cugb.deviceinfo.deviceinfo;

/**
 * Created by xuyong on 16/4/2.
 */

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class ScreenFragment extends BaseFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    @Bind(R.id.tv_screen_inch)
    TextView mTvScreenInch;
    @Bind(R.id.tv_screen_dpi)
    TextView mTvScreenDpi;
    @Bind(R.id.tv_px_width)
    TextView mTvPxWidth;
    @Bind(R.id.tv_px_height)
    TextView mTvPxHeight;
    @Bind(R.id.tv_dpi_width)
    TextView mTvDpiWidth;
    @Bind(R.id.tv_dpi_height)
    TextView mTvDpiHeight;
    @Bind(R.id.tv_inch_width)
    TextView mTvInchWidth;
    @Bind(R.id.tv_inch_height)
    TextView mTvInchHeight;
    @Bind(R.id.tv_statusbar_height)
    TextView mTvStatusBarHeight;
    @Bind(R.id.tv_keybar_height)
    TextView mTvKeybarHeight;

    String mSreenInch;
    String mScreenDpi;
    String mWidthSize;
    String mHeightSize;
    String mStatusBarHeight;
    String mKeyBarHeight;

    NumberFormat ddf1;
    DisplayMetrics dm;
    double screenInches;

    public ScreenFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ScreenFragment newInstance(int sectionNumber) {
        ScreenFragment fragment = new ScreenFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        dm = new DisplayMetrics();
        dm = this.getActivity().getResources().getDisplayMetrics();

        ddf1 = NumberFormat.getNumberInstance();
        ddf1.setMaximumFractionDigits(2);

        int sdk = Integer.valueOf(Build.VERSION.SDK_INT);
        int width;
        int height;

        if (sdk >= Build.VERSION_CODES.JELLY_BEAN_MR1 ) {
            Point point = new Point();
            getActivity().getWindowManager().getDefaultDisplay().getRealSize(point);
            DisplayMetrics dm = getResources().getDisplayMetrics();
            width = point.x;
            height = point.y;
            double x1 = Math.pow(point.x / dm.xdpi, 2);
            double y1 = Math.pow(point.y / dm.ydpi, 2);
            screenInches = Math.sqrt(x1 + y1);
        } else {
            width = dm.widthPixels;
            height = dm.heightPixels;
            double x = Math.pow(width,2);
            double y = Math.pow(height, 2);
            double diagonal = Math.sqrt(x + y);

            int dens=dm.densityDpi;
            screenInches = diagonal/(double)dens;
        }

        double screenInchesX = width/(double)dm.xdpi;
        double screenInchesY = height/(double)dm.ydpi;


        mSreenInch = getActivity().getResources().getString(R.string.screen_size);
        mScreenDpi = getActivity().getResources().getString(R.string.screen_dpi);

        mWidthSize = getActivity().getResources().getString(R.string.screen_size_x);
        mHeightSize = getActivity().getResources().getString(R.string.screen_size_y);
        mStatusBarHeight = getActivity().getResources().getString(R.string.statusbar_height);
        mKeyBarHeight = getActivity().getResources().getString(R.string.keybar_height);

        mTvScreenInch.setText(String.format(mSreenInch, String.valueOf(ddf1.format(screenInches))));
        mTvScreenDpi.setText(String.format(mScreenDpi, String.valueOf(dm.densityDpi)));

        mTvPxWidth.setText(String.format(mWidthSize, String.valueOf(dm.widthPixels)));
        //mTvPxHeight.setText(String.format(mHeightSize, String.valueOf(dm.heightPixels)));
        mTvPxHeight.setText(String.format(mHeightSize, String.valueOf(height)));

        mTvDpiWidth.setText(String.format(mWidthSize, String.valueOf(ddf1.format(dm.xdpi))));
        mTvDpiHeight.setText(String.format(mHeightSize, String.valueOf(ddf1.format(dm.ydpi))));

        mTvInchWidth.setText(String.format(mWidthSize, String.valueOf(ddf1.format(screenInchesX))));
        mTvInchHeight.setText(String.format(mHeightSize, String.valueOf(ddf1.format(screenInchesY))));

        mTvStatusBarHeight.setText(String.format(mStatusBarHeight, String.valueOf(getStatusBarHeight())));
        mTvKeybarHeight.setText(String.format(mKeyBarHeight, String.valueOf(height-dm.heightPixels)));
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getActivity().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
