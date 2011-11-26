/* JS
 * jquery.tooltip
 * Elliot Lings
 * www.elliotlings.com/jquery/tooltip/
 */
(function($){
	$.fn.tooltip = function(dire, content, start) {
			dire = $.extend({direction : 'n'}, dire || {});
			var active;
      var tooltipE = $('.tooltip').length;      
      
			/* Bounce Settings */
			var gap = 15;
			var bounce = 25;
			
			switch(dire.direction){
				case 'n':
				default:
					var direction_class = 'n';
				break;
				case 's':
					var direction_class = 's';
				break;
				case 'e':
					var direction_class = 'e';
				break;
				case 'w':
					var direction_class = 'w';
				break;
			}

			if((start == 'true' && (tooltipE == 0))){
							
				var box = $.data(this, 'active.help');
				if(!box) {
					box = $('<table class="tooltip ' + direction_class + '" cellpadding="0" cellspacing="0">'
                    + '<tr>'
                        + '<td class="tl"></td>'
                          + '<td class="tc"></td>'
                          + '<td class="tr"></td>'
                      + '</tr>'
                        + '<td class="ml"></td>'
                          + '<td class="mc">'
                            + '<div class="tooltip-content">'
                                + content +
                               '</div>'
                            + '<div class="close">'
                            + '<a class="close-tooltip" onclick="javascript:closeDialog(this);" href="#">Close</a>'
                          + '</td>'
                          + '<td class="mr"></td>'
                      + '<tr>'
                        + '<td class="bl"></td>'
                          + '<td class="bc"></td>'
                          + '<td class="br"></td>'
                      + '</tr>'
                 + '</table>');
					box.css({position: 'absolute', zIndex: 9999});
					$.data(this, 'active.help', box);
				}
				box.remove().css({top: 0, left: 0, visibility: 'hidden', display: 'block'}).appendTo(document.body);
				var pos = $.extend({}, $(this).offset(), {width:this.offsetWidth});
				
				box.css({ top: pos.top + this[0].offsetHeight / 2 - box[0].offsetHeight / 2, left: pos.left + this.width() / 2 - box[0].offsetWidth / 2});
				
				switch(dire.direction){
					  case 'n':
					  default:
						box.animate({top: pos.top - box[0].offsetHeight - bounce, left: pos.left + this.width() / 2 - box[0].offsetWidth / 2});
						box.animate({top: pos.top - box[0].offsetHeight - gap});
					  break;
					  case 's':
						box.animate({top: pos.top + this.height() + bounce, left: pos.left + this.width() / 2 -  box[0].offsetWidth / 2});
						box.animate({top: pos.top + this.height() + gap});
					  break;
					  case 'e':
						box.animate({top: pos.top + this.height() / 2 - box[0].offsetHeight / 2, left: pos.left + this.width() + bounce});
						box.animate({left: pos.left + this.width() + gap});
					  break;
					  case 'w':
						box.animate({top: pos.top + this[0].offsetHeight / 2 - box[0].offsetHeight / 2, left: pos.left - box[0].offsetWidth - bounce});
						box.animate({left: pos.left - box[0].offsetWidth - gap});
					  break;	
				}
				
				box.css({visibility: 'visible'});
				
				this.click(function(){
				
          if($(".tooltip").length == 0){
            $(this).tooltip({direction: dire.direction},content,'true');
          }
          
          return false;
          
				});

				} else {
				
				this.click(function(){
									
          if($(".tooltip").length == 0){
            $(this).tooltip({direction: dire.direction},content,'true');
          }
				
          return false;
				
				});	
			}			

	};
		  
})(jQuery);
function closeDialog(element){
  $(".tooltip").remove();
}