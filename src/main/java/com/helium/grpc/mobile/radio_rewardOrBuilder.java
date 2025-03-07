// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/poc_mobile.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc.mobile;

public interface radio_rewardOrBuilder extends
    // @@protoc_insertion_point(interface_extends:helium.poc_mobile.radio_reward)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Public key of the hotspot of the radio
   * </pre>
   *
   * <code>bytes hotspot_key = 1;</code>
   * @return The hotspotKey.
   */
  com.google.protobuf.ByteString getHotspotKey();

  /**
   * <pre>
   * cbsd Id of the radio
   * </pre>
   *
   * <code>string cbsd_id = 2;</code>
   * @return The cbsdId.
   */
  java.lang.String getCbsdId();
  /**
   * <pre>
   * cbsd Id of the radio
   * </pre>
   *
   * <code>string cbsd_id = 2;</code>
   * @return The bytes for cbsdId.
   */
  com.google.protobuf.ByteString
      getCbsdIdBytes();

  /**
   * <pre>
   * Amount credited to the hotspot for data transfer in bones
   * This value will always be zero
   * </pre>
   *
   * <code>uint64 dc_transfer_reward = 3 [deprecated = true];</code>
   * @deprecated helium.poc_mobile.radio_reward.dc_transfer_reward is deprecated.
   *     See service/poc_mobile.proto;l=574
   * @return The dcTransferReward.
   */
  @java.lang.Deprecated long getDcTransferReward();

  /**
   * <pre>
   * Amount credited to the hotspot for poc
   * </pre>
   *
   * <code>uint64 poc_reward = 4;</code>
   * @return The pocReward.
   */
  long getPocReward();

  /**
   * <pre>
   * Accumulated coverage points for the radio
   * </pre>
   *
   * <code>uint64 coverage_points = 5;</code>
   * @return The coveragePoints.
   */
  long getCoveragePoints();

  /**
   * <pre>
   * Seniority timestamp for this radio in seconds since the unix epoch
   * </pre>
   *
   * <code>uint64 seniority_timestamp = 6;</code>
   * @return The seniorityTimestamp.
   */
  long getSeniorityTimestamp();

  /**
   * <pre>
   * UUID of the coverage object used to reward this radio
   * </pre>
   *
   * <code>bytes coverage_object = 7;</code>
   * @return The coverageObject.
   */
  com.google.protobuf.ByteString getCoverageObject();

  /**
   * <pre>
   * only used for wifi radios, all others should have a value of 1.0
   * value is 0.0 to 1.0 multiplied by 1000
   * </pre>
   *
   * <code>uint32 location_trust_score_multiplier = 8;</code>
   * @return The locationTrustScoreMultiplier.
   */
  int getLocationTrustScoreMultiplier();

  /**
   * <pre>
   * based on speedtest averages of speedtests during 48 hour period from end of
   * rewardable period
   * value is 0.0 to 1.0 multiplied by 1000
   * </pre>
   *
   * <code>uint32 speedtest_multiplier = 9;</code>
   * @return The speedtestMultiplier.
   */
  int getSpeedtestMultiplier();

  /**
   * <pre>
   * list of all boosted hexes covered by this radio
   * </pre>
   *
   * <code>repeated .helium.poc_mobile.boosted_hex boosted_hexes = 10;</code>
   */
  java.util.List<com.helium.grpc.mobile.boosted_hex> 
      getBoostedHexesList();
  /**
   * <pre>
   * list of all boosted hexes covered by this radio
   * </pre>
   *
   * <code>repeated .helium.poc_mobile.boosted_hex boosted_hexes = 10;</code>
   */
  com.helium.grpc.mobile.boosted_hex getBoostedHexes(int index);
  /**
   * <pre>
   * list of all boosted hexes covered by this radio
   * </pre>
   *
   * <code>repeated .helium.poc_mobile.boosted_hex boosted_hexes = 10;</code>
   */
  int getBoostedHexesCount();
  /**
   * <pre>
   * list of all boosted hexes covered by this radio
   * </pre>
   *
   * <code>repeated .helium.poc_mobile.boosted_hex boosted_hexes = 10;</code>
   */
  java.util.List<? extends com.helium.grpc.mobile.boosted_hexOrBuilder> 
      getBoostedHexesOrBuilderList();
  /**
   * <pre>
   * list of all boosted hexes covered by this radio
   * </pre>
   *
   * <code>repeated .helium.poc_mobile.boosted_hex boosted_hexes = 10;</code>
   */
  com.helium.grpc.mobile.boosted_hexOrBuilder getBoostedHexesOrBuilder(
      int index);
}
