// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/poc_lora.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc;

public interface invalid_detailsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:helium.poc_lora.invalid_details)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string denylist_tag = 1;</code>
   * @return Whether the denylistTag field is set.
   */
  boolean hasDenylistTag();
  /**
   * <code>string denylist_tag = 1;</code>
   * @return The denylistTag.
   */
  java.lang.String getDenylistTag();
  /**
   * <code>string denylist_tag = 1;</code>
   * @return The bytes for denylistTag.
   */
  com.google.protobuf.ByteString
      getDenylistTagBytes();

  com.helium.grpc.invalid_details.DataCase getDataCase();
}
