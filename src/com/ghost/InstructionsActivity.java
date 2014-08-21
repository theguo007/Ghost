package com.ghost;

import android.support.v4.app.Fragment;

public class InstructionsActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new InstructionsFragment();
	}
}
