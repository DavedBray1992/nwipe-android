package com.example.nwipe_android;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class WipeJobTest {
    private WipeJob wipeJob;

    @Before
    public void setUp() {
        this.wipeJob = new WipeJob();
        this.wipeJob.totalBytes = 1000;
    }

    @Test
    public void testNotStarted() {
        this.wipeJob.wipedBytes = 0;
        Assert.assertEquals(this.wipeJob.getCurrentPassPercentageCompletion(), 0);
    }

    @Test
    public void testStarted() {
        this.wipeJob.wipedBytes = 500;
        Assert.assertEquals(this.wipeJob.getCurrentPassPercentageCompletion(), 50);
    }

    @Test
    public void testFinished() {
        this.wipeJob.wipedBytes = this.wipeJob.totalBytes;
        Assert.assertEquals(this.wipeJob.getCurrentPassPercentageCompletion(), 100);
    }

    @Test
    public void testJobWithBlanking() {
        WipeJob wipeJob = new WipeJob();
        wipeJob.number_passes = 4;
        wipeJob.blank = true;
        wipeJob.passes_completed = 4;

        Assert.assertEquals(wipeJob.toString(), "Blanking pass");

        wipeJob.verifying = true;
        Assert.assertEquals(wipeJob.toString(), "Verifying Blanking pass");

        wipeJob.completed = true;
        wipeJob.succeeded = true;
        Assert.assertEquals(wipeJob.toString(), "Succeeded");
    }

}
