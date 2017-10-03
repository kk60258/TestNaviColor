package nineg.com.testnavi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewGroup vp1 = findViewById(R.id.group1);
        final ViewGroup vp2 = findViewById(R.id.group2);
        final ViewGroup vp3 = findViewById(R.id.group3);
        final ViewGroup vp4 = findViewById(R.id.group4);


        Button button = findViewById(R.id.startact);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TestActivity.class);
                CheckBox cb = findCheckBox(vp1);
                if (cb.isChecked()) {
                    intent.putExtra(TestActivity.FULL_SCREEN, true);
                } else {
                    intent.putExtra(TestActivity.FULL_SCREEN, false);
                }


                cb = findCheckBox(vp2);
                if (cb.isChecked()) {
                    intent.putExtra(TestActivity.NAVI_COLOR, TestActivity.NAVIGATION_BAR_TRANSPARENT);
                }

                cb = findCheckBox(vp3);
                if (cb.isChecked()) {
                    intent.putExtra(TestActivity.NAVI_COLOR, TestActivity.NAVIGATION_BAR_TRANSLUCENT);
                }

                cb = findCheckBox(vp4);
                if (cb.isChecked()) {
                    intent.putExtra(TestActivity.NAVI_COLOR, TestActivity.NAVIGATION_BAR_YELLOW);
                }

                MainActivity.this.startActivity(intent);
            }
        });
    }

    private CheckBox findCheckBox(ViewGroup vp) {
        int count = vp.getChildCount();
        for(int i = 0; i < count; i ++) {
            if (vp.getChildAt(i) instanceof CheckBox)
                return (CheckBox) vp.getChildAt(i);
        }
        return null;
    }
}
