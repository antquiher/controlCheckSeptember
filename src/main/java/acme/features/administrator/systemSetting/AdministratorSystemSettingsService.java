package acme.features.administrator.systemSetting;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemSetting.SystemSettings;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorSystemSettingsService implements AbstractShowService<Administrator, SystemSettings> {


		@Autowired
		protected AdministratorSystemSettingsRepository repository;


		@Override
		public boolean authorise(final Request<SystemSettings> request) {
			assert request != null;
			
		

			return true;
		}

		@Override
		public void unbind(final Request<SystemSettings> request, final SystemSettings entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "spamThreshold", "defaultCurrency", "acceptedCurrencies", "spamTuples");
		}

		@Override
		public SystemSettings findOne(final Request<SystemSettings> request) {
			assert request != null;
			
			SystemSettings result=null;

			final Optional<SystemSettings> optResult = this.repository.findOne();
			if (optResult.isPresent()) {
				result = optResult.get();
			}
			

			assert result != null;
			
			return result;
		}
	}

