package com.ghost;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.content.Context;

public class JSONSerializer {

    private Context mContext;
    private String mFilename;

    public JSONSerializer(Context c, String f) {
        mContext = c;
        mFilename = f;
    }

    public void saveStats(Stats mStats) throws JSONException, IOException {
        // Build an array in JSON
        JSONArray array = new JSONArray();
        array.put(mStats.toJSON());

        // Write the file to disk
        Writer writer = null;
        try {
            OutputStream out = mContext
                .openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
        }
    }

	public JSONObject loadStats() throws IOException, JSONException {	    
	    JSONObject x = null;
		BufferedReader reader = null;
	    try {
	        // Open and read the file into a StringBuilder
	        InputStream in = mContext.openFileInput(mFilename);
	        reader = new BufferedReader(new InputStreamReader(in));
	        StringBuilder jsonString = new StringBuilder();
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            // Line breaks are omitted and irrelevant
	            jsonString.append(line);
	        }
	        // Parse the JSON using JSONTokener
	        JSONArray array = (JSONArray) new JSONTokener(jsonString.toString())
	            .nextValue();
	        x = array.getJSONObject(0);
	        // Build the array of crimes from JSONObjects
	    } catch (FileNotFoundException e) {
	        // Ignore this one; it happens when starting fresh
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	    return x;
	}
}