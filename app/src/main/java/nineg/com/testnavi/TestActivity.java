package nineg.com.testnavi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class TestActivity extends Activity {

    public static final String FULL_SCREEN = "fullscreen";
    public static final String NAVI_COLOR = "navi_color";

    public static final int STATUS_BAR_TRANSPARENT = 101;
    public static final int STATUS_BAR_TRANSLUCENT = 102;

    public static final int NAVIGATION_BAR_TRANSPARENT = 101;
    public static final int NAVIGATION_BAR_TRANSLUCENT = 102;
    public static final int NAVIGATION_BAR_YELLOW = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StringBuilder sb = new StringBuilder();
        Intent intent = getIntent();
        int navicolor = intent.getIntExtra(NAVI_COLOR, 104);
        switch (navicolor) {
            case NAVIGATION_BAR_TRANSPARENT:
                sb.append("transparent");
            case NAVIGATION_BAR_TRANSLUCENT:
                sb = new StringBuilder();
                sb.append("translucent");
                setupWindowStyle(navicolor);
                break;
            case NAVIGATION_BAR_YELLOW:
                sb.append("custom color");
                setYellowNavi();
                break;
            default:
        }

        if (intent.getBooleanExtra(FULL_SCREEN, false)) {
            setFullScreen();
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append("fullscreen");
        }

        setContentView(R.layout.test_main);

        TextView tv = findViewById(R.id.text1);
        tv.setText(sb.toString());

    }

    public void setupWindowStyle(int navigationBarState) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) { // TODO: change to M if using M build
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            switch (navigationBarState) {
                case NAVIGATION_BAR_TRANSLUCENT:
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                    break;
                case NAVIGATION_BAR_TRANSPARENT:
                default:
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    getWindow().setNavigationBarColor(Color.TRANSPARENT);
                    break;
            }
        } else if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) { // >= KitKat
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void setYellowNavi() {
        setTheme(R.style.YellowNavi);
    }

    private void setFullScreen() {
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

    }
}
