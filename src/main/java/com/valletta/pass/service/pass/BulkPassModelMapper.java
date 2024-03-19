package com.valletta.pass.service.pass;

import com.valletta.pass.controller.admin.BulkPassRequest;
import com.valletta.pass.repository.pass.BulkPassEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BulkPassModelMapper {

    BulkPassModelMapper INSTANCE = Mappers.getMapper(BulkPassModelMapper.class);

    List<BulkPass> map(List<BulkPassEntity> passEntities);

    //    BulkPass map(BulkPassEntity passEntity);
    BulkPassEntity map(BulkPassRequest bulkPassRequest);
}
