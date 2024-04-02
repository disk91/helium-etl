// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mapper.proto

// Protobuf Java Version: 3.25.1
package com.helium.grpc.mobile;

public interface mapper_scan_v1OrBuilder extends
    // @@protoc_insertion_point(interface_extends:helium.mapper_scan_v1)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>uint32 scan_counter = 1;</code>
   * @return The scanCounter.
   */
  int getScanCounter();

  /**
   * <code>.helium.mapper_gps_v1 gps = 2;</code>
   * @return Whether the gps field is set.
   */
  boolean hasGps();
  /**
   * <code>.helium.mapper_gps_v1 gps = 2;</code>
   * @return The gps.
   */
  com.helium.grpc.mobile.mapper_gps_v1 getGps();
  /**
   * <code>.helium.mapper_gps_v1 gps = 2;</code>
   */
  com.helium.grpc.mobile.mapper_gps_v1OrBuilder getGpsOrBuilder();

  /**
   * <code>repeated .helium.mapper_scan_result results = 3;</code>
   */
  java.util.List<com.helium.grpc.mobile.mapper_scan_result> 
      getResultsList();
  /**
   * <code>repeated .helium.mapper_scan_result results = 3;</code>
   */
  com.helium.grpc.mobile.mapper_scan_result getResults(int index);
  /**
   * <code>repeated .helium.mapper_scan_result results = 3;</code>
   */
  int getResultsCount();
  /**
   * <code>repeated .helium.mapper_scan_result results = 3;</code>
   */
  java.util.List<? extends com.helium.grpc.mobile.mapper_scan_resultOrBuilder> 
      getResultsOrBuilderList();
  /**
   * <code>repeated .helium.mapper_scan_result results = 3;</code>
   */
  com.helium.grpc.mobile.mapper_scan_resultOrBuilder getResultsOrBuilder(
      int index);
}