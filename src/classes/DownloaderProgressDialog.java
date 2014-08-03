package classes;

import android.app.ProgressDialog;
import android.content.Context;
import com.example.goodmorning.R;

public class DownloaderProgressDialog extends ProgressDialog {

	public DownloaderProgressDialog(Context context) {
		super(context);

		this.setIndeterminate(true);
		this.setCancelable(false);
		this.setInverseBackgroundForced(false);
		this.setCanceledOnTouchOutside(false);
		this.setMessage(context.getResources().getString(R.string.downloader_dialog_title));
		this.setTitle(context.getResources().getString(R.string.downloader_dialog_message)); 
	}

}
