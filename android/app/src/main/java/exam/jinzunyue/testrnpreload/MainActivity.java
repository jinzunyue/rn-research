package exam.jinzunyue.testrnpreload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import exam.jinzunyue.testrnpreload.rnControler.RNPreLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (RNPreLoader.getReactRootView("MyReactNativeApp") == null) {
                    RNPreLoader.preLoader(MainActivity.this, "MyReactNativeApp");
                }
                startActivity(new Intent(MainActivity.this, MyReactActivity.class));
            }
        });
    }
}
