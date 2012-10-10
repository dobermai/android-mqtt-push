package de.eclipsemagazin.mqtt.push;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;


public class PushCallback implements MqttCallback {

    private ContextWrapper context;

    public PushCallback(ContextWrapper context) {

        this.context = context;
    }

    @Override
    public void connectionLost(Throwable cause) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void messageArrived(MqttTopic topic, MqttMessage message) throws Exception {

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.drawable.snow,
                "A new notification", System.currentTimeMillis());
        // Hide the notification after its selected
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        Intent intent = new Intent(context, BlackIceActivity.class);
        PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 0);
        notification.setLatestEventInfo(context, "New Push",
                new String(message.getPayload()), activity);
        notification.number += 1;
        notificationManager.notify(0, notification);
    }

    @Override
    public void deliveryComplete(MqttDeliveryToken token) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
