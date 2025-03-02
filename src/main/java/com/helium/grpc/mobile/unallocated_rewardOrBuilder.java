// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/poc_mobile.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc.mobile;

public interface unallocated_rewardOrBuilder extends
    // @@protoc_insertion_point(interface_extends:helium.poc_mobile.unallocated_reward)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * the reward type representing a reward category to which an unallocated
   * amount exists
   * </pre>
   *
   * <code>.helium.poc_mobile.unallocated_reward_type reward_type = 1;</code>
   * @return The enum numeric value on the wire for rewardType.
   */
  int getRewardTypeValue();
  /**
   * <pre>
   * the reward type representing a reward category to which an unallocated
   * amount exists
   * </pre>
   *
   * <code>.helium.poc_mobile.unallocated_reward_type reward_type = 1;</code>
   * @return The rewardType.
   */
  com.helium.grpc.mobile.unallocated_reward_type getRewardType();

  /**
   * <pre>
   * &#47; Amount in bones unallocated
   * </pre>
   *
   * <code>uint64 amount = 2;</code>
   * @return The amount.
   */
  long getAmount();
}
