
package com.jivosite.example

import com.google.firebase.messaging.RemoteMessage
import com.jivosite.sdk.Jivo
import com.jivosite.sdk.push.JivoFirebaseMessagingService

class PushService : JivoFirebaseMessagingService()  {

    override fun onNewToken(token: String) {

        Jivo.updatePushToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {

        if (!Jivo.handleRemoteMessage(message)) {
            //Выполнить обработку сообщения.
        }
    }
}
