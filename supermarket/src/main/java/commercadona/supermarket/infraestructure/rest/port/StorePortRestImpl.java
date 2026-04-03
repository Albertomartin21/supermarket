package commercadona.supermarket.infraestructure.rest.port;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import commercadona.supermarket.application.mapper.StoreMapper;
import commercadona.supermarket.domain.model.StoreData;
import commercadona.supermarket.domain.rest.StorePortRest;
import commercadona.supermarket.infraestructure.rest.ApiClientWeb;
import commercadona.supermarket.infraestructure.rest.contract.StoreDataRest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StorePortRestImpl implements StorePortRest{

    private ApiClientWeb apiClientWeb;
    private StoreMapper storeMapper;

    @Value("${api.reports.url}")
    private String url;

    @Value("${api.reports.pageSize}")
    private int pageSize;

    public StorePortRestImpl(ApiClientWeb apiClientWeb, StoreMapper storeMapper){
        this.apiClientWeb = apiClientWeb;
        this.storeMapper = storeMapper;
    }


    @Override
    public StoreData geStoreDataById(Long idStore) {
        StoreDataRest storeDataRest = apiClientWeb.getRequest(String.format(url, idStore), StoreDataRest.class);
        return storeMapper.mapStoreDataRestToStoreData(storeDataRest);
    }
    
}
