// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/poc_mobile.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc.mobile;

public interface coverage_object_v1OrBuilder extends
    // @@protoc_insertion_point(interface_extends:helium.poc_mobile.coverage_object_v1)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.helium.poc_mobile.coverage_object_req_v1 coverage_object = 1;</code>
   * @return Whether the coverageObject field is set.
   */
  boolean hasCoverageObject();
  /**
   * <code>.helium.poc_mobile.coverage_object_req_v1 coverage_object = 1;</code>
   * @return The coverageObject.
   */
  com.helium.grpc.mobile.coverage_object_req_v1 getCoverageObject();
  /**
   * <code>.helium.poc_mobile.coverage_object_req_v1 coverage_object = 1;</code>
   */
  com.helium.grpc.mobile.coverage_object_req_v1OrBuilder getCoverageObjectOrBuilder();

  /**
   * <code>.helium.poc_mobile.coverage_object_validity validity = 2;</code>
   * @return The enum numeric value on the wire for validity.
   */
  int getValidityValue();
  /**
   * <code>.helium.poc_mobile.coverage_object_validity validity = 2;</code>
   * @return The validity.
   */
  com.helium.grpc.mobile.coverage_object_validity getValidity();
}
