// Generated by view binder compiler. Do not edit!
package com.capstone.cookpocket.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.capstone.cookpocket.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityStoreBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final FloatingActionButton fabAddProduct;

  @NonNull
  public final TextView headerStore;

  @NonNull
  public final ImageView imgAccount;

  @NonNull
  public final ImageView ivBackStore;

  @NonNull
  public final ImageView ivStore;

  @NonNull
  public final LinearLayout llProfile;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final RecyclerView rvStore;

  @NonNull
  public final TextView tvGmailAccount;

  @NonNull
  public final TextView tvUsernameAccount;

  private ActivityStoreBinding(@NonNull ConstraintLayout rootView,
      @NonNull FloatingActionButton fabAddProduct, @NonNull TextView headerStore,
      @NonNull ImageView imgAccount, @NonNull ImageView ivBackStore, @NonNull ImageView ivStore,
      @NonNull LinearLayout llProfile, @NonNull ConstraintLayout main,
      @NonNull RecyclerView rvStore, @NonNull TextView tvGmailAccount,
      @NonNull TextView tvUsernameAccount) {
    this.rootView = rootView;
    this.fabAddProduct = fabAddProduct;
    this.headerStore = headerStore;
    this.imgAccount = imgAccount;
    this.ivBackStore = ivBackStore;
    this.ivStore = ivStore;
    this.llProfile = llProfile;
    this.main = main;
    this.rvStore = rvStore;
    this.tvGmailAccount = tvGmailAccount;
    this.tvUsernameAccount = tvUsernameAccount;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityStoreBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityStoreBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_store, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityStoreBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.fab_add_product;
      FloatingActionButton fabAddProduct = ViewBindings.findChildViewById(rootView, id);
      if (fabAddProduct == null) {
        break missingId;
      }

      id = R.id.header_store;
      TextView headerStore = ViewBindings.findChildViewById(rootView, id);
      if (headerStore == null) {
        break missingId;
      }

      id = R.id.img_account;
      ImageView imgAccount = ViewBindings.findChildViewById(rootView, id);
      if (imgAccount == null) {
        break missingId;
      }

      id = R.id.iv_back_store;
      ImageView ivBackStore = ViewBindings.findChildViewById(rootView, id);
      if (ivBackStore == null) {
        break missingId;
      }

      id = R.id.iv_store;
      ImageView ivStore = ViewBindings.findChildViewById(rootView, id);
      if (ivStore == null) {
        break missingId;
      }

      id = R.id.ll_profile;
      LinearLayout llProfile = ViewBindings.findChildViewById(rootView, id);
      if (llProfile == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.rv_store;
      RecyclerView rvStore = ViewBindings.findChildViewById(rootView, id);
      if (rvStore == null) {
        break missingId;
      }

      id = R.id.tv_gmail_account;
      TextView tvGmailAccount = ViewBindings.findChildViewById(rootView, id);
      if (tvGmailAccount == null) {
        break missingId;
      }

      id = R.id.tv_username_account;
      TextView tvUsernameAccount = ViewBindings.findChildViewById(rootView, id);
      if (tvUsernameAccount == null) {
        break missingId;
      }

      return new ActivityStoreBinding((ConstraintLayout) rootView, fabAddProduct, headerStore,
          imgAccount, ivBackStore, ivStore, llProfile, main, rvStore, tvGmailAccount,
          tvUsernameAccount);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}