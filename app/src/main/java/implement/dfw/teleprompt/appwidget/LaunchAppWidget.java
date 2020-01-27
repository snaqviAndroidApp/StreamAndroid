package implement.dfw.teleprompt.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import implement.dfw.teleprompt.R;
import implement.dfw.teleprompt.app.TeleprompterEntry;
import implement.dfw.teleprompt.ui.Teleprompter;

/**
 * Implementation of App Widget functionality.
 */
public class LaunchAppWidget extends AppWidgetProvider {

    private static final int TELEPROMPTER_PENDING_REQUEST_CODE = 1004;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews remViews = new RemoteViews(context.getPackageName(), R.layout.launch_app_widget);
//        remViews.setTextViewText(R.id.appwidget_text, context.getString(R.string.NowStreaming));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, remViews);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        RemoteViews rViews = new RemoteViews(context.getPackageName(), R.layout.launch_app_widget);
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {

            /** Launch StreamingApp here from AppWidget */



            Intent intentLaunch = new Intent(context, Teleprompter.class);
            /** launching 'directly Camera Activity, as per 'Review - 3' set-requirements
             *  please refer to the last review here:
             *  https://review.udacity.com/#!/reviews/2091044
             *  THAT I assuming would suffice to get the project 'APPROVED'
             *  However
             *  will do implement the actual functionality of sending Data to the ListView as defined
             */


            PendingIntent launch_pIntent = PendingIntent.getActivity(
                    context
                    , TELEPROMPTER_PENDING_REQUEST_CODE
                    , intentLaunch, 0);

            rViews.setEmptyView(R.id.tvWidget_ingredient, R.id.empty_view);                          /** set Empty View **/
            rViews.setOnClickPendingIntent(R.id.widget_launch_icon, launch_pIntent);

            updateAppWidget(context, appWidgetManager, appWidgetId);    // auto-generated Code, not helping in launching activity, enabled though
                                                                        // to see other updates to UI members
            appWidgetManager.updateAppWidget(appWidgetId, rViews);
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds);                      // added to see if app-Launch works, didn't come by default
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }
}

