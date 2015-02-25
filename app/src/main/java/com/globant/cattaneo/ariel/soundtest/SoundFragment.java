package com.globant.cattaneo.ariel.soundtest;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class SoundFragment extends Fragment {

    private SoundPool mSoundPool;
    private int alertSoundID;
    private float mVolume;

    public SoundFragment() {
    }

    private void playAlert() {
        mSoundPool.play(alertSoundID, mVolume, mVolume, 10, 0, 1.0f);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        rootView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAlert();
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        float normalVolume = ((AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE))
                .getStreamVolume(AudioManager.STREAM_ALARM);
        float maxVolume = ((AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE))
                .getStreamMaxVolume(AudioManager.STREAM_ALARM);

        mVolume = normalVolume / maxVolume;

        // Load the sounds
        mSoundPool = new SoundPool(1, AudioManager.STREAM_ALARM, 0);
        alertSoundID = mSoundPool.load(getActivity(), R.raw.alert, 1);
    }
}
