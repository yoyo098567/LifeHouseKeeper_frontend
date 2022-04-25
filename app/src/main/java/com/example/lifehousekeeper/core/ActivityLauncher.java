package com.example.lifehousekeeper.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ActivityLauncher {
    public static void go(Context context, Class<? extends Activity> activityClass, Bundle args) {
        Intent intent = new Intent(context, activityClass);
        if (args != null) {
            intent.putExtras(args);
        }
        context.startActivity(intent);
    }
}
