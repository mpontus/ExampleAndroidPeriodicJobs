package app.example.schedulers.utils;

import android.content.Context;
import app.example.schedulers.ExampleJobService;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

public class SchedulerUtils {
  private static final String JOB_TAG = "example-job";
  private static final int REMINDER_INTERVAL_SECONDS = 60;
  private static final int REMINDER_FLEXTIME_SECONDS = 60;

  public static void scheduleJob(Context context) {
    FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));

    Job job = dispatcher.newJobBuilder()
        .setService(ExampleJobService.class)
        .setTag(JOB_TAG)
        .setRecurring(true)
        .setLifetime(Lifetime.FOREVER)
        .setTrigger(Trigger.executionWindow(
            REMINDER_INTERVAL_SECONDS,
            REMINDER_INTERVAL_SECONDS + REMINDER_FLEXTIME_SECONDS))
        .setReplaceCurrent(true)
        .build();

    dispatcher.mustSchedule(job);
  }
}
