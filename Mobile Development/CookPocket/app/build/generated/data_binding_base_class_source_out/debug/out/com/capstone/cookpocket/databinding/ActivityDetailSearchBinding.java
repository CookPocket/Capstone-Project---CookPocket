// Generated by view binder compiler. Do not edit!
package com.capstone.cookpocket.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.capstone.cookpocket.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDetailSearchBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonPesan;

  @NonNull
  public final ImageView imageViewCart;

  @NonNull
  public final ImageView ivBack;

  @NonNull
  public final ImageView ivFavorite;

  @NonNull
  public final ImageView ivMainImage;

  @NonNull
  public final TextView labelBahan;

  @NonNull
  public final TextView labelPembuatan;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final TextView tvDeskripsiBahan;

  @NonNull
  public final TextView tvDeskripsiPembuatan;

  @NonNull
  public final TextView tvJudul;

  private ActivityDetailSearchBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button buttonPesan, @NonNull ImageView imageViewCart, @NonNull ImageView ivBack,
      @NonNull ImageView ivFavorite, @NonNull ImageView ivMainImage, @NonNull TextView labelBahan,
      @NonNull TextView labelPembuatan, @NonNull LinearLayout linearLayout,
      @NonNull TextView tvDeskripsiBahan, @NonNull TextView tvDeskripsiPembuatan,
      @NonNull TextView tvJudul) {
    this.rootView = rootView;
    this.buttonPesan = buttonPesan;
    this.imageViewCart = imageViewCart;
    this.ivBack = ivBack;
    this.ivFavorite = ivFavorite;
    this.ivMainImage = ivMainImage;
    this.labelBahan = labelBahan;
    this.labelPembuatan = labelPembuatan;
    this.linearLayout = linearLayout;
    this.tvDeskripsiBahan = tvDeskripsiBahan;
    this.tvDeskripsiPembuatan = tvDeskripsiPembuatan;
    this.tvJudul = tvJudul;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDetailSearchBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDetailSearchBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_detail_search, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDetailSearchBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_pesan;
      Button buttonPesan = ViewBindings.findChildViewById(rootView, id);
      if (buttonPesan == null) {
        break missingId;
      }

      id = R.id.imageView_cart;
      ImageView imageViewCart = ViewBindings.findChildViewById(rootView, id);
      if (imageViewCart == null) {
        break missingId;
      }

      id = R.id.iv_back;
      ImageView ivBack = ViewBindings.findChildViewById(rootView, id);
      if (ivBack == null) {
        break missingId;
      }

      id = R.id.iv_favorite;
      ImageView ivFavorite = ViewBindings.findChildViewById(rootView, id);
      if (ivFavorite == null) {
        break missingId;
      }

      id = R.id.iv_main_image;
      ImageView ivMainImage = ViewBindings.findChildViewById(rootView, id);
      if (ivMainImage == null) {
        break missingId;
      }

      id = R.id.label_bahan;
      TextView labelBahan = ViewBindings.findChildViewById(rootView, id);
      if (labelBahan == null) {
        break missingId;
      }

      id = R.id.label_pembuatan;
      TextView labelPembuatan = ViewBindings.findChildViewById(rootView, id);
      if (labelPembuatan == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.tv_deskripsi_bahan;
      TextView tvDeskripsiBahan = ViewBindings.findChildViewById(rootView, id);
      if (tvDeskripsiBahan == null) {
        break missingId;
      }

      id = R.id.tv_deskripsi_pembuatan;
      TextView tvDeskripsiPembuatan = ViewBindings.findChildViewById(rootView, id);
      if (tvDeskripsiPembuatan == null) {
        break missingId;
      }

      id = R.id.tv_judul;
      TextView tvJudul = ViewBindings.findChildViewById(rootView, id);
      if (tvJudul == null) {
        break missingId;
      }

      return new ActivityDetailSearchBinding((ConstraintLayout) rootView, buttonPesan,
          imageViewCart, ivBack, ivFavorite, ivMainImage, labelBahan, labelPembuatan, linearLayout,
          tvDeskripsiBahan, tvDeskripsiPembuatan, tvJudul);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}