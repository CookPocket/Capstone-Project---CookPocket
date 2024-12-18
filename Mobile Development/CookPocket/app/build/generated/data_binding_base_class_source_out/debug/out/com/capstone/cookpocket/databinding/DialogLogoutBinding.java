// Generated by view binder compiler. Do not edit!
package com.capstone.cookpocket.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.capstone.cookpocket.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogLogoutBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnNo;

  @NonNull
  public final Button btnYes;

  @NonNull
  public final TextView tvMessage;

  @NonNull
  public final TextView tvTitle;

  private DialogLogoutBinding(@NonNull LinearLayout rootView, @NonNull Button btnNo,
      @NonNull Button btnYes, @NonNull TextView tvMessage, @NonNull TextView tvTitle) {
    this.rootView = rootView;
    this.btnNo = btnNo;
    this.btnYes = btnYes;
    this.tvMessage = tvMessage;
    this.tvTitle = tvTitle;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogLogoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogLogoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_logout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogLogoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnNo;
      Button btnNo = ViewBindings.findChildViewById(rootView, id);
      if (btnNo == null) {
        break missingId;
      }

      id = R.id.btnYes;
      Button btnYes = ViewBindings.findChildViewById(rootView, id);
      if (btnYes == null) {
        break missingId;
      }

      id = R.id.tvMessage;
      TextView tvMessage = ViewBindings.findChildViewById(rootView, id);
      if (tvMessage == null) {
        break missingId;
      }

      id = R.id.tvTitle;
      TextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      return new DialogLogoutBinding((LinearLayout) rootView, btnNo, btnYes, tvMessage, tvTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
