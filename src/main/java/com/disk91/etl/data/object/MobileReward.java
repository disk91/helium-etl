package com.disk91.etl.data.object;


import fr.ingeniousthings.tools.ClonnableObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Sharded;
import org.springframework.data.mongodb.core.mapping.ShardingStrategy;

@Document(collection = "etl_mob_rewards")
@CompoundIndexes({
        @CompoundIndex(name = "hotspotId", def = "{'hotspotId' : 'hashed'}"),
        @CompoundIndex(name = "ownerId", def = "{'ownerId' : 'hashed'}"),
        @CompoundIndex(name = "hotspotId_Id", def = "{'hotspotId' : 1, 'id' : 1}"),
        @CompoundIndex(name = "hotspotId_SPeriod", def = "{'hotspotId' : 1, 'startPeriod' : 1}"),
        @CompoundIndex(name = "ownerId_SPeriod", def = "{'ownerId' : 1, 'startPeriod' : 1}")
})
@Sharded(shardKey = { "hotspotId", "id" }, shardingStrategy = ShardingStrategy.RANGE)
public class MobileReward implements ClonnableObject<MobileReward> {

    @Id
    private String id;
    private int version;
    private String hotspotId;       // use 0x00000 for subscribers ans service providers and 0x000 for unallocated

    private String ownerId;         // or subscriber ID or service provider ID and 0x00000 for unallocated

    private long startPeriod;
    private long endPeriod;

    private long radioPocReward = 0;
    private long radioCovergePoint = 0;
    private long dcTransferReward = 0;
    private long dcTransferPrice = 0;   // price of mobile * 10^6 used when calculated reward

    private long subscriberReward = 0;
    private long serviceProviderReward = 0;
    private long unallocatedPoc = 0;
    private long unallocatedDiscovery = 0;
    private long unallocatedMapper = 0;
    private long unallocatedService = 0;
    private long unallocatedOracle = 0;
    private long unallocatedData = 0;

    public MobileReward clone() {
        MobileReward c = new MobileReward();
        c.setId(id);
        c.setVersion(version);
        c.setHotspotId(hotspotId);
        c.setOwnerId(ownerId);
        c.setStartPeriod(startPeriod);
        c.setEndPeriod(endPeriod);
        c.setRadioPocReward(radioPocReward);
        c.setRadioCovergePoint(radioCovergePoint);
        c.setDcTransferReward(dcTransferReward);
        c.setDcTransferPrice(dcTransferPrice);
        c.setSubscriberReward(subscriberReward);
        c.setServiceProviderReward(serviceProviderReward);
        c.setUnallocatedPoc(unallocatedPoc);
        c.setUnallocatedDiscovery(unallocatedDiscovery);
        c.setUnallocatedMapper(unallocatedMapper);
        c.setUnallocatedService(unallocatedService);
        c.setUnallocatedOracle(unallocatedOracle);
        c.setUnallocatedData(unallocatedData);
        return c;
    }



    // ---


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getHotspotId() {
        return hotspotId;
    }

    public void setHotspotId(String hotspotId) {
        this.hotspotId = hotspotId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public long getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(long startPeriod) {
        this.startPeriod = startPeriod;
    }

    public long getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(long endPeriod) {
        this.endPeriod = endPeriod;
    }

    public long getRadioPocReward() {
        return radioPocReward;
    }

    public void setRadioPocReward(long radioPocReward) {
        this.radioPocReward = radioPocReward;
    }

    public long getRadioCovergePoint() {
        return radioCovergePoint;
    }

    public void setRadioCovergePoint(long radioCovergePoint) {
        this.radioCovergePoint = radioCovergePoint;
    }

    public long getDcTransferReward() {
        return dcTransferReward;
    }

    public void setDcTransferReward(long dcTransferReward) {
        this.dcTransferReward = dcTransferReward;
    }

    public long getDcTransferPrice() {
        return dcTransferPrice;
    }

    public void setDcTransferPrice(long dcTransferPrice) {
        this.dcTransferPrice = dcTransferPrice;
    }

    public long getSubscriberReward() {
        return subscriberReward;
    }

    public void setSubscriberReward(long subscriberReward) {
        this.subscriberReward = subscriberReward;
    }

    public long getServiceProviderReward() {
        return serviceProviderReward;
    }

    public void setServiceProviderReward(long serviceProviderReward) {
        this.serviceProviderReward = serviceProviderReward;
    }

    public long getUnallocatedPoc() {
        return unallocatedPoc;
    }

    public void setUnallocatedPoc(long unallocatedPoc) {
        this.unallocatedPoc = unallocatedPoc;
    }

    public long getUnallocatedDiscovery() {
        return unallocatedDiscovery;
    }

    public void setUnallocatedDiscovery(long unallocatedDiscovery) {
        this.unallocatedDiscovery = unallocatedDiscovery;
    }

    public long getUnallocatedMapper() {
        return unallocatedMapper;
    }

    public void setUnallocatedMapper(long unallocatedMapper) {
        this.unallocatedMapper = unallocatedMapper;
    }

    public long getUnallocatedService() {
        return unallocatedService;
    }

    public void setUnallocatedService(long unallocatedService) {
        this.unallocatedService = unallocatedService;
    }

    public long getUnallocatedOracle() {
        return unallocatedOracle;
    }

    public void setUnallocatedOracle(long unallocatedOracle) {
        this.unallocatedOracle = unallocatedOracle;
    }

    public long getUnallocatedData() {
        return unallocatedData;
    }

    public void setUnallocatedData(long unallocatedData) {
        this.unallocatedData = unallocatedData;
    }
}
