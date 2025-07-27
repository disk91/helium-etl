#!/bn/bash

# initialisation : we need the collection _tmp existing with the right index (execute one time only)
#docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_hotspots.renameCollection(\"etl_hotspots_tmp\", {dropTarget:true})" helium-etl'
#docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_hsIndex.renameCollection(\"etl_hsIndex_tmp\", {dropTarget:true})" helium-etl'
#docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_rewards.renameCollection(\"etl_rewards_tmp\", {dropTarget:true})" helium-etl'
#docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_mob_rewards.renameCollection(\"etl_mob_rewards_tmp\", {dropTarget:true})" helium-etl'
#docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_params.renameCollection(\"etl_params_tmp\", {dropTarget:true})" helium-etl'
#docker start etl

# Reload Data in tmp collection
docker exec mongo-router-01 sh -c 'mongorestore --archive=/dump/hotspot.dump --gzip --nsFrom="helium-etl.etl_hotspots" --nsTo="helium-etl.etl_hotspots_tmp"'
docker exec mongo-router-01 sh -c 'mongorestore --archive=/dump/hsIndex.dump --gzip --nsFrom="helium-etl.etl_hsIndex" --nsTo="helium-etl.etl_hsIndex_tmp"'
docker exec mongo-router-01 sh -c 'mongorestore --archive=/dump/rewards.dump --numInsertionWorkersPerCollection=4 --gzip --nsFrom="helium-etl.etl_rewards" --nsTo="helium-etl.etl_rewards_tmp"'
docker exec mongo-router-01 sh -c 'mongorestore --archive=/dump/mob_rewards.dump --numInsertionWorkersPerCollection=4 --gzip --nsFrom="helium-etl.etl_mob_rewards" --nsTo="helium-etl.etl_mob_rewards_tmp"'

# shutdown the application
docker stop -t 10800 etl

# Rename current collection
docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_hotspots.renameCollection(\"etl_hotspots_old\", {dropTarget:true})" helium-etl'
docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_hsIndex.renameCollection(\"etl_hsIndex_old\", {dropTarget:true})" helium-etl'
docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_rewards.renameCollection(\"etl_rewards_old\", {dropTarget:true})" helium-etl'
docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_mob_rewards.renameCollection(\"etl_mob_rewards_old\", {dropTarget:true})" helium-etl'
docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_params.renameCollection(\"etl_params_old\", {dropTarget:true})" helium-etl'

docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_hotspots_tmp.renameCollection(\"etl_hotspots\", {dropTarget:true})" helium-etl'
docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_hsIndex_tmp.renameCollection(\"etl_hsIndex\", {dropTarget:true})" helium-etl'
docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_rewards_tmp.renameCollection(\"etl_rewards\", {dropTarget:true})" helium-etl'
docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_mob_rewards_tmp.renameCollection(\"etl_mob_rewards\", {dropTarget:true})" helium-etl'
docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_params_tmp.renameCollection(\"etl_params\", {dropTarget:true})" helium-etl'

# restart the application
docker start etl

# Purge collections to reload
docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_hotspots_old.deleteMany({})" helium-etl'
docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_hsIndex_old.deleteMany({})" helium-etl'
docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_rewards_old.deleteMany({})" helium-etl'
docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_mob_rewards_old.deleteMany({})" helium-etl'
# Get the storage back
docker exec shard-01-node-a sh -c 'mongosh --eval "db.runCommand({ compact: \"etl_hotspots_old\" })" helium-etl'
docker exec shard-02-node-a sh -c 'mongosh --eval "db.runCommand({ compact: \"etl_hotspots_old\" })" helium-etl'
docker exec shard-03-node-a sh -c 'mongosh --eval "db.runCommand({ compact: \"etl_hotspots_old\" })" helium-etl'
docker exec shard-01-node-a sh -c 'mongosh --eval "db.runCommand({ compact: \"etl_hsIndex_old\" })" helium-etl'
docker exec shard-02-node-a sh -c 'mongosh --eval "db.runCommand({ compact: \"etl_hsIndex_old\" })" helium-etl'
docker exec shard-03-node-a sh -c 'mongosh --eval "db.runCommand({ compact: \"etl_hsIndex_old\" })" helium-etl'
docker exec shard-01-node-a sh -c 'mongosh --eval "db.runCommand({ compact: \"etl_rewards_old\" })" helium-etl'
docker exec shard-02-node-a sh -c 'mongosh --eval "db.runCommand({ compact: \"etl_rewards_old\" })" helium-etl'
docker exec shard-03-node-a sh -c 'mongosh --eval "db.runCommand({ compact: \"etl_rewards_old\" })" helium-etl'
docker exec shard-01-node-a sh -c 'mongosh --eval "db.runCommand({ compact: \"etl_mob_rewards_old\" })" helium-etl'
docker exec shard-02-node-a sh -c 'mongosh --eval "db.runCommand({ compact: \"etl_mob_rewards_old\" })" helium-etl'
docker exec shard-03-node-a sh -c 'mongosh --eval "db.runCommand({ compact: \"etl_mob_rewards_old\" })" helium-etl'

# Rename for next load
docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_hotspots_old.renameCollection(\"etl_hotspots_tmp\", {dropTarget:true})" helium-etl'
docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_hsIndex_old.renameCollection(\"etl_hsIndex_tmp\", {dropTarget:true})" helium-etl'
docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_rewards_old.renameCollection(\"etl_rewards_tmp\", {dropTarget:true})" helium-etl'
docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_mob_rewards_old.renameCollection(\"etl_mob_rewards_tmp\", {dropTarget:true})" helium-etl'
docker exec mongo-router-01 sh -c 'mongosh --eval "db.etl_params_old.renameCollection(\"etl_params_tmp\", {dropTarget:true})" helium-etl'
