package libo.com.social.ui.main.mall.adapter.multiTypeAdapter.viewHolder;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;
;import libo.com.social.R;

public class TextViewHolder extends BaseViewHolder<String> {

    private TextView mText;

    public TextViewHolder(ViewGroup parent) {
        super(parent, R.layout.holder_text);
    }

    @Override
    public void onInitializeView() {
        super.onInitializeView();
        mText = findViewById(R.id.tv);
    }

    @Override
    public void setData(String object) {
        super.setData(object);
        mText.setText(object);
    }

    @Override
    public void onItemViewClick(String object) {
        Log.i("TextViewHolder", "onItemViewClick");
    }
}

