// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mapper.proto

// Protobuf Java Version: 3.25.1
package com.helium.grpc.mobile;

public interface mapper_scanOrBuilder extends
    // @@protoc_insertion_point(interface_extends:helium.mapper_scan)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.helium.mapper_scan_v1 scan_v1 = 1;</code>
   * @return Whether the scanV1 field is set.
   */
  boolean hasScanV1();
  /**
   * <code>.helium.mapper_scan_v1 scan_v1 = 1;</code>
   * @return The scanV1.
   */
  com.helium.grpc.mobile.mapper_scan_v1 getScanV1();
  /**
   * <code>.helium.mapper_scan_v1 scan_v1 = 1;</code>
   */
  com.helium.grpc.mobile.mapper_scan_v1OrBuilder getScanV1OrBuilder();

  com.helium.grpc.mobile.mapper_scan.VersionCase getVersionCase();
}
