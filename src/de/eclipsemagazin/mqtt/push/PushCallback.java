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
        //We should reconnect here
    }

    @Override
    public void messageArrived(MqttTopic topic, MqttMessage message) throws Exception {

        final NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        final Notification notification = new Notification(R.drawable.snow,
                "Black Ice Warning!", System.currentTimeMillis());

        // Hide the notification after its selected
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        final Intent intent = new Intent(context, BlackIceActivity.class);
        final PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 0);
        notification.setLatestEventInfo(context, "Black Ice Warning", "Outdoor temperature is " +
                new String(message.getPayload()) + "°", activity);
        notification.number += 1;
        notificationManager.notify(0, notification);
    }

    @Override
    public void deliveryComplete(MqttDeliveryToken token) {
        //We do not need this because we do not publish
    }
}
