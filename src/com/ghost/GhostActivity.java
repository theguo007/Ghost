package com.ghost;

import android.support.v4.app.Fragment;

public class GhostActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {		 
		return new GhostFragment();
	}

	

}
