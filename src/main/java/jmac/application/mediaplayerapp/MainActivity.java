package jmac.application.mediaplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Button playButton;
    private SeekBar mSeekbar;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        mSeekbar = (SeekBar) findViewById ( R.id.mSeekBar );

        mediaPlayer = new MediaPlayer ( );
        mediaPlayer = MediaPlayer.create ( getApplicationContext ( ), R.raw.mj);
        mSeekbar.setMax ( mediaPlayer.getDuration () );

        mSeekbar.setOnSeekBarChangeListener ( new SeekBar.OnSeekBarChangeListener ( ) {
            @Override
            public void onProgressChanged ( SeekBar seekBar, int progress, boolean fromUser ) {

                if (fromUser) {
                    mediaPlayer.seekTo ( progress );
                }

            }

            @Override
            public void onStartTrackingTouch ( SeekBar seekBar ) {

            }

            @Override
            public void onStopTrackingTouch ( SeekBar seekBar ) {

            }
        } );

        mediaPlayer.setOnCompletionListener ( new MediaPlayer.OnCompletionListener ( ) {
            @Override
            public void onCompletion ( MediaPlayer mp ) {
                int duration = mp.getDuration ();
                String mDuration = String.valueOf ( duration );

                Toast.makeText ( getApplicationContext (), "duration" + mDuration, Toast.LENGTH_LONG).show ();
            }
        } );


        playButton = (Button) findViewById ( R.id.playButton );
        playButton.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                if (mediaPlayer.isPlaying ( )) {
                    pauseMusic ( );

                } else {
                    startMusic ( );
                }
            }

            public void pauseMusic () {
                if (mediaPlayer != null) {
                    mediaPlayer.pause ( );
                    playButton.setText ( "Play" );
                }
            }

        } );
    }

    public void startMusic () {
        if (mediaPlayer != null) {
            mediaPlayer.start ();
            playButton.setText ( "Pause" );
        }

        }

    @Override
    protected void onDestroy () {
        if (mediaPlayer != null && mediaPlayer.isPlaying ()) {
            mediaPlayer.stop ();
            mediaPlayer.release ();
            mediaPlayer = null;
        }
        super.onDestroy ( );
    }
}





