package de.eclipsemagazin.mqtt.push;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BlackIceActivity extends Activity {

    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createService();
            }
        });
    }


    private void createService() {

        intent = new Intent(this, MQTTService.class);
        startService(intent);
    }
}
