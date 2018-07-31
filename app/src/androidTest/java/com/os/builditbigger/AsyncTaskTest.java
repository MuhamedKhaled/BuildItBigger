package com.os.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.os.builditbigger.free.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {
    private static final String TAG = AsyncTaskTest.class.getSimpleName();

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() throws ExecutionException, InterruptedException {
        final JokerApiAsyncTask asyncTask = new JokerApiAsyncTask(rule.getActivity());

        String result = asyncTask.execute().get();

        assertNotNull(result);
        assertNotEquals(result, "");
    }
}
