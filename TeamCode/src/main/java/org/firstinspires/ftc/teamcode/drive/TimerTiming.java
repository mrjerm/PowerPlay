package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.TimeUnit;

public class TimerTiming {

    public static class Timer {
        private ElapsedTime time;
        private long timerLength;
        private long pauseTime;
        private TimeUnit timerUnit;
        private boolean timerActivated;

        public Timer(long timerLength, TimeUnit timerUnit) {
            this.timerLength = timerLength;
            this.timerUnit = timerUnit;
            this.time = new ElapsedTime();
            time.reset();
        }


        public Timer(long timerLength) {
            this(timerLength, TimeUnit.MILLISECONDS);
        }

        public void start() {
            time.reset();
            pauseTime = 0;
            timerActivated = true;
        }

        public void pause() {
            if (timerActivated) {
                pauseTime = time.nanoseconds();
                timerActivated = false;
            }
        }

        public void resume() {
            if (!timerActivated) {
                time = new ElapsedTime(System.nanoTime() - pauseTime);
                timerActivated = true;
            }
        }

        public long elapsedTime() {
            if (timerActivated)
                return time.time(timerUnit);
            else
                return timerUnit.convert(pauseTime, TimeUnit.NANOSECONDS);
        }

        public long timeRemaining() {
            return timerLength - elapsedTime();
        }

        public boolean done() {
            return elapsedTime() >= timerLength;
        }

        public boolean isTimerOn() {
            return timerActivated;
        }

    }

    public class Rate {
        private ElapsedTime time;
        private long rate;

        public Rate (long rateMilliseconds) {
            rate = rateMilliseconds;
            time = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        }

        public void reset() {
            time.reset();
        }

        public boolean atTime() {
            boolean done = (time.milliseconds() >= rate);
            time.reset();
            return done;
        }
    }
}
