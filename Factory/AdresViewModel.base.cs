// 

using System;
using System.Collections.Generic;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.ComponentModel.DataAnnotations;

using Rektorat.Model;

namespace Rektorat.ViewModel
{

    public partial class AdresViewModel 
    {
        public Adres Adres { get; private set; }

        [Display(Name = "Id")]
        public int? Id
        {
            get
            {
                return Adres.Id;
            }
            set
            {
                Adres.Id = value;
                RaisePropertyChanged();
                ValidateModelProperty(this, nameof(Id), Id);
            }
        }

        [Display(Name = "Fid")]
        public int? Fid
        {
            get
            {
                return Adres.Fid;
            }
            set
            {
                Adres.Fid = value;
                RaisePropertyChanged();
                ValidateModelProperty(this, nameof(Fid), Fid);
            }
        }

        [Display(Name = "Rodzaj skierowania")]
        public int? TypSkierowania
        {
            get
            {
                return Adres.TypSkierowania;
            }
            set
            {
                Adres.TypSkierowania = value;
                RaisePropertyChanged();
                ValidateModelProperty(this, nameof(TypSkierowania), TypSkierowania);
            }
        }

        [Display(Name = "Typ miejsca zakwaterowania")]
        public int? TypMiejscaZakwaterowania
        {
            get
            {
                return Adres.TypMiejscaZakwaterowania;
            }
            set
            {
                Adres.TypMiejscaZakwaterowania = value;
                RaisePropertyChanged();
                ValidateModelProperty(this, nameof(TypMiejscaZakwaterowania), TypMiejscaZakwaterowania);
            }
        }

        [Required(ErrorMessage = "Pole jest wymagane.")]
        [Display(Name = "Osoba")]
        public int? Osoba
        {
            get
            {
                return Adres.Osoba;
            }
            set
            {
                Adres.Osoba = value;
                RaisePropertyChanged();
                ValidateModelProperty(this, nameof(Osoba), Osoba);
            }
        }

        [Display(Name = "WazneOd")]
        public DateTime? WazneOd
        {
            get
            {
                return Adres.WazneOd;
            }
            set
            {
                if(Adres.WazneOd != value)
                {
                    Adres.WazneOd = value;
                    RaisePropertyChanged();
                    ValidateModelProperty(this, nameof(WazneOd), WazneOd);
                }
            }
        }

        [Display(Name = "WazneDo")]
        public DateTime? WazneDo
        {
            get
            {
                return Adres.WazneDo;
            }
            set
            {
                if(Adres.WazneDo != value)
                {
                    Adres.WazneDo = value;
                    RaisePropertyChanged();
                    ValidateModelProperty(this, nameof(WazneDo), WazneDo);
                }
            }
        }
    }

}
