package com.ghost;

import android.support.v4.app.Fragment;

public class GhostMainActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new GhostMainFragment();
	}

}
