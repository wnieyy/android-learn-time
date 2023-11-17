package adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.InformationFragment;
import com.example.myapplication.NotificationFragment;
import com.example.myapplication.SettingsFragment;

public class MyFragmentAdapter extends FragmentStateAdapter {
    public MyFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {

            case 0:
                return new InformationFragment();
            case 1:
                return new NotificationFragment();
            case 2:
                return new SettingsFragment();
            default:
                return new InformationFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
